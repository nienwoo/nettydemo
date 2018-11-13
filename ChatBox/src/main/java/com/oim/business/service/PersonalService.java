package com.oim.business.service;

import java.awt.EventQueue;

import com.oim.app.AppContext;
import com.oim.business.handler.VideoHandler;
import com.oim.business.manage.AppManage;
import com.oim.business.manage.PersonalManage;
import com.oim.business.manage.VideoManage;
import com.oim.common.app.service.Service;
import com.oim.common.config.ConfigManage;
import com.oim.common.config.data.ConnectConfigData;
import com.oim.common.task.ExecuteTask;
import com.oim.net.message.Message;
import com.oim.net.message.UserMessage;
import com.oim.net.message.VideoMessage;
import com.oim.net.message.data.VideoAddress;
import com.oim.ui.view.LoginView;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月7日 下午8:37:18
 * @version 0.0.1
 */
public class PersonalService extends Service {

	public PersonalService(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 登录后回掉
	 * 
	 * @param userMessage
	 */
	public void loginBack(final UserMessage userMessage) {

		boolean isLogin = UserMessage.result_code_true.equals(userMessage.getResultCode());
		final LoginView loginView = this.getSingleView(LoginView.class);
		if (isLogin) {
			loginView.setVisible(false);
			ExecuteTask et = new ExecuteTask() {

				@Override
				public void execute() {
					AppManage am = appContext.getManage(AppManage.class);
					am.initApp(userMessage.getUser());
					PersonalManage pm = appContext.getManage(PersonalManage.class);
					pm.initPersonal(userMessage.getUser());
					initBeatMessage();
					initVideoService();
				}
			};
			appContext.add(et);
		} else {
			this.appContext.getConnectThread().setAutoCloseConnectTime(1);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					loginView.showPromptMessage(userMessage.getResultMessage());
				}
			});
		}
		loginView.showWaiting(false);
	}

	/**
	 * 设置发送心跳包(当tcp太久没发送消息的时候，可能已经断开连接了，这个用来保持连接)(不熟mina的心跳机制，就这样了，懒得去研究了)
	 */
	private void initBeatMessage() {
		Message beatData = new Message();
		beatData.setController("001");
		beatData.setMethod("0001");
		this.appContext.getDataWriteThread().setBeatData(beatData);
		this.appContext.getDataWriteThread().setSendBeatData(true);
	}

	private void initVideoService() {
		DataBackActionAdapter dataBackAction = new DataBackActionAdapter() {
			@Override
			public void backExecute(Data data) {
				if (data instanceof VideoMessage) {
					VideoMessage message = (VideoMessage) data;
					if (VideoMessage.result_code_true.equals(message.getResultCode())) {
						ConnectConfigData ccd = (ConnectConfigData) ConfigManage.get(ConnectConfigData.path, ConnectConfigData.class);
						VideoAddress videoAddress = message.getVideoAddress();
						videoAddress.setAddress(ccd.getBusinessAddress());
						setVideoAddress(videoAddress);
					}
				}
			}
		};
		VideoHandler vh = this.appContext.getHandler(VideoHandler.class);
		vh.getVideoServerPort(dataBackAction);
	}

	private void setVideoAddress(VideoAddress videoAddress) {
		VideoManage vm = this.appContext.getManage(VideoManage.class);
		vm.setVideoServerAddress(videoAddress);
	}
}
