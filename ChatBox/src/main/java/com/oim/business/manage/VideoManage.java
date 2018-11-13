package com.oim.business.manage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.oim.app.AppContext;
import com.oim.bean.User;
import com.oim.business.constant.VideoConstant;
import com.oim.business.handler.VideoHandler;
import com.oim.common.app.manage.Manage;
import com.oim.common.box.PersonalBox;
import com.oim.common.task.ExecuteTask;
import com.oim.common.util.ByteUtil;
import com.oim.net.message.VideoMessage;
import com.oim.net.message.data.UserData;
import com.oim.net.message.data.VideoAddress;
import com.oim.net.thread.SocketData;
import com.oim.net.thread.SocketDataHandler;
import com.oim.net.thread.SocketThread;
import com.oim.ui.VideoFrame;
import com.oim.ui.component.event.ExecuteAction;
import com.oim.ui.video.VideoShowPanel;
import com.only.OnlyMessageBox;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月15日 下午7:52:44
 * @version 0.0.1
 */
public class VideoManage extends Manage {

	private SocketThread videoSocketThread = new SocketThread();
	private VideoAddress videoServerAddress;
	VideoFrame videoFrame = new VideoFrame();
	private Map<String, VideoShowPanel> vspMap = new ConcurrentHashMap<String, VideoShowPanel>();
	private Map<String, VideoAddress> vaMap = new ConcurrentHashMap<String, VideoAddress>();
	private ExecuteAction shutExecuteAction;
	private ExecuteAction agreeExecuteAction;

	public VideoManage(AppContext appContext) {
		super(appContext);
		init();
		initEvent();
	}

	private void init() {
		videoFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		videoSocketThread.start();
		Timer timer = new Timer();
		timer.schedule(new UpdateVideoTask(), 1000, 20000);
		ImageThread imageThread = new ImageThread();
		imageThread.start();
	}

	private void initEvent() {
		videoSocketThread.addSocketDataHandler(new SocketDataHandler() {
			public void received(SocketData socketData) {
				setVideoData(socketData);
			}
		});
		videoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		shutExecuteAction = new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof VideoShowPanel) {
					UserData userData = ((VideoShowPanel) value).getAttribute(UserData.class);
					shut(userData.getId());
				}
				return null;
			}
		};
		agreeExecuteAction = new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof VideoShowPanel) {
					UserData userData = ((VideoShowPanel) value).getAttribute(UserData.class);
					agree(userData);
				}
				return null;
			}
		};
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		if (!vaMap.isEmpty()) {
			int button = OnlyMessageBox.createQuestionMessageBox(videoFrame, "关闭", "是否关闭所有视频通信？").open();
			if (OnlyMessageBox.YES_OPTION == button) {
				videoFrame.stopVideo();
				videoFrame.removeAllTab();
				handerVideoFrame();
			}
		} else {
			videoFrame.stopVideo();
			videoFrame.removeAllTab();
			handerVideoFrame();
		}
	}

	private void agree(UserData userData) {
		String userId = userData.getId();
		VideoShowPanel vsp = getVideoShowPanel(userData);
		vsp.showGetRequest(false);
		User user = PersonalBox.get(User.class);
		String sendId = (null == user) ? null : user.getId();
		VideoHandler vh = this.appContext.getHandler(VideoHandler.class);
		vh.responseVideo(sendId, userId, VideoConstant.action_type_agree);

		DataBackAction dataBackAction = new DataBackActionAdapter() {
			@Override
			public void backExecute(Data data) {
				if (data instanceof VideoMessage) {// 获取用户信息成功后，显示其聊天信息
					VideoMessage message = (VideoMessage) data;
					String userId = message.getReceiveId();
					VideoAddress videoAddress = message.getVideoAddress();
					setUserVideoAddress(userId, videoAddress);
				}
			}
		};

		vh.getUserVideoAddress(userId, dataBackAction);
	}

	private void shut(String userId) {
		remove(userId);
		User user = PersonalBox.get(User.class);
		String sendId = (null == user) ? null : user.getId();
		VideoHandler vh = this.appContext.getHandler(VideoHandler.class);
		vh.responseVideo(sendId, userId, VideoConstant.action_type_shut);
	}

	private void setUserVideoAddress(String userId, VideoAddress videoAddress) {
		vaMap.put(userId, videoAddress);
		videoFrame.startVideo();
	}

	public void getAgree(String userId, VideoAddress videoAddress) {
		setUserVideoAddress(userId, videoAddress);
	}

	public void getShut(String userId) {
		remove(userId);
	}

	public void remove(String key) {
		vaMap.remove(key);
		VideoShowPanel vsp = vspMap.remove(key);
		if (null != vsp) {
			videoFrame.removeTab(vsp);
		}
		handerVideoFrame();
	}

	public void handerVideoFrame() {
		if (vaMap.isEmpty()) {
			videoFrame.stopVideo();
		}
		if (videoFrame.isTabEmpty()) {
			videoFrame.setVisible(false);
		}
	}

	public void setVideoServerAddress(VideoAddress videoServerAddress) {
		this.videoServerAddress = videoServerAddress;
		ExecuteTask et = new ExecuteTask() {

			@Override
			public void execute() {
				for (int i = 0; i < 5; i++) {
					handlerVideoServerAddress();
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		this.appContext.add(et);
	}

	private void handlerVideoServerAddress() {
		User user = PersonalBox.get(User.class);
		String address = (null == videoServerAddress) ? null : videoServerAddress.getAddress();
		int port = (null == videoServerAddress) ? -1 : videoServerAddress.getPort();
		String userId = (null == user) ? null : user.getId();
		if (null != address && !"".endsWith(address) && -1 != port && null != userId && !"".equals(userId)) {
			SocketAddress socketAddress = new InetSocketAddress(address, port);
			videoSocketThread.send(userId.getBytes(), socketAddress);
		}
	}

	class UpdateVideoTask extends TimerTask {

		@Override
		public void run() {
			handlerVideoServerAddress();
		}
	}

	class ImageThread extends Thread {

		public void run() {
			while (true) {
				createImage();
				threadSleep(100);
			}
		}

		private void threadSleep(long time) {
			try {
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void createImage() {
			if (!vaMap.isEmpty() && videoFrame.isStartVideo()) {
				try {
					BufferedImage image = videoFrame.getVideoImage();
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);// 转换成JPEG图像格式
					encoder.encode(image);
					output.close();

					User user = PersonalBox.get(User.class);
					String userId = (null == user) ? "" : user.getId();

					byte[] keyByte = userId.getBytes();
					byte[] data = output.toByteArray();
					int size = keyByte.length;
					byte[] sizeBytes = ByteUtil.intToBytes(size);

					byte[] allData = ByteUtil.byteMerger(ByteUtil.byteMerger(sizeBytes, keyByte), data);
					List<VideoAddress> list = new ArrayList<VideoAddress>(vaMap.values());
					for (VideoAddress va : list) {
						SocketAddress socketAddress = new InetSocketAddress(va.getAddress(), va.getPort());
						videoSocketThread.send(allData, socketAddress);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				threadSleep(1000);
			}
		}
	}

	public void showSendVideoFrame(UserData userData) {
		if (!vaMap.containsKey(userData.getId())) {
			VideoShowPanel vsp = getVideoShowPanel(userData);
			vsp.showGetRequest(false);
			videoFrame.addTab(userData.getNickname(), vsp);
			videoFrame.setVisible(true);
		}
	}

	public void showGetVideoFrame(UserData userData) {
		if (!vaMap.containsKey(userData.getId())) {
			VideoShowPanel vsp = getVideoShowPanel(userData);
			vsp.showGetRequest(true);
			videoFrame.addTab(userData.getNickname(), vsp);
			videoFrame.setVisible(true);
		}
	}

	private VideoShowPanel getVideoShowPanel(UserData userData) {
		VideoShowPanel vsp = vspMap.get(userData.getId());
		if (null == vsp) {
			vsp = new VideoShowPanel();
			vsp.addAttribute(UserData.class, userData);
			vsp.addShutExecuteAction(shutExecuteAction);
			vsp.addAgreeExecuteAction(agreeExecuteAction);
			vspMap.put(userData.getId(), vsp);
		}
		return vsp;
	}

	private void setVideoData(SocketData socketData) {

		byte[] bytes = socketData.getBytes();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		byte[] sizeBytes = new byte[4];
		try {
			in.read(sizeBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int size = ByteUtil.bytesToInt(sizeBytes);
		byte[] keyByte = new byte[size];
		try {
			in.read(keyByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String key = new String(keyByte);
		VideoShowPanel vsp = vspMap.get(key);
		if (null != vsp) {
			Image image;
			try {

				image = ImageIO.read(in);
				image = image.getScaledInstance(480, 320, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(image);
				vsp.setVideoIcon(icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
