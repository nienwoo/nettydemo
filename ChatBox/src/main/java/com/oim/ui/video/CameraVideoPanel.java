package com.oim.ui.video;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.io.IOException;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;
import javax.media.format.VideoFormat;
import javax.swing.JPanel;

import net.sf.fmj.media.cdp.GlobalCaptureDevicePlugger;
import net.sf.fmj.ui.application.ContainerPlayer;
import net.sf.fmj.ui.application.PlayerPanelPrefs;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年6月6日 上午9:43:44
 * @version 0.0.1
 */
public class CameraVideoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ContainerPlayer containerPlayer = new ContainerPlayer(this);
	private String mediaLocator = null;
	private PlayerPanelPrefs prefs = new PlayerPanelPrefs();
	private boolean start = false;

	public CameraVideoPanel() {
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new CardLayout());
	}

	public void chooseDevice(Frame frame) {
		MediaLocator mediaLocator = CaptureDeviceBrowser.run(frame); // 弹出摄像头设备选择
		this.mediaLocator = (null == mediaLocator) ? null : mediaLocator.toExternalForm();
		if (start) {
			startVideo();
		}
	}

	public void chooseDevice() {
		chooseDevice(getFrameParent(this));
	}

	@SuppressWarnings("rawtypes")
	public boolean startVideo() {

		GlobalCaptureDevicePlugger.addCaptureDevices();
		Vector deviceVector = CaptureDeviceManager.getDeviceList(new VideoFormat(VideoFormat.RGB));
		if (deviceVector == null || deviceVector.size() == 0) {
			return start = false;
		}

		if (null == mediaLocator || "".equals(mediaLocator)) {
			if (deviceVector.size() > 1) {
				chooseDevice();
			} else {
				CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) deviceVector.get(0);
				MediaLocator m = captureDeviceInfo.getLocator();
				mediaLocator = m.toExternalForm();
			}
		} else {
			boolean has = false;
			for (int i = 0; i < deviceVector.size(); i++) {
				CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) deviceVector.get(i);
				MediaLocator m = captureDeviceInfo.getLocator();
				if (mediaLocator.equals(m.toExternalForm())) {
					has = true;
					break;
				}
			}
			if (!has) {
				return start = false;
			}
		}

		if (mediaLocator == null || "".equals(mediaLocator)) {
			return start = false;
		}

		try {
			try {
				containerPlayer.setMediaLocation(mediaLocator, prefs.autoPlay);
				start = true;
			} catch (NoDataSourceException e) {
				e.printStackTrace();
				return start = false;
			} catch (NoPlayerException e) {
				e.printStackTrace();
				return start = false;
			} catch (IOException e) {
				e.printStackTrace();
				return start = false;
			}
		} catch (Throwable e) {
			System.out.println(" catch error.");
			// 希望在此获得A里发生的异常，不论什么方法，只能有获得到A里的sum异常message就行
		}
		return start = true;
	}

	public void stopVideo() {
		containerPlayer.stop();
		start = false;
	}

	public String getMediaLocator() {
		return mediaLocator;
	}

	public boolean isStart() {
		return start;
	}

	private Frame getFrameParent(Container container) {
		if (container == null) {
			return null;
		}
		if (container instanceof Frame) {
			return (Frame) container;
		}
		if (container instanceof Dialog) {
			return null;
		}
		return getFrameParent(container.getParent());
	}
}
