package com.oim.app;

import org.apache.log4j.Logger;

import com.oim.business.handler.PersonalHandler;
import com.oim.common.app.controller.ControllerFactory;
import com.oim.common.app.handler.SendHandler;
import com.oim.common.app.handler.SendHandlerFactory;
import com.oim.common.app.manage.Manage;
import com.oim.common.app.manage.ManageFactory;
import com.oim.common.app.service.Service;
import com.oim.common.app.service.ServiceFactory;
import com.oim.common.app.view.AbstractView;
import com.oim.common.app.view.ViewFactory;
import com.oim.common.task.ExecuteTask;
import com.oim.common.task.QueueTaskThread;
import com.oim.net.connect.MessageHandler;
import com.oim.net.connect.SocketConnector;
import com.oim.net.server.Handler;
import com.oim.net.thread.HeadPulseThread;
import com.oim.ui.view.LoginView;
import com.only.net.action.ConnectBackAction;
import com.only.net.action.ConnectStatusAction;
import com.only.net.connect.ConnectThread;
import com.only.net.connect.WriteHandler;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataSentAction;
import com.only.net.data.bean.HandlerData;
import com.only.net.thread.DataReadThread;
import com.only.net.thread.DataWriteThread;

/**
 * 这是程序的上下文，方便各个模块之间调用
 * 
 * @author XiaHui
 * @date 2015年3月6日 上午9:30:30
 */
public class AppContext {
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	private Handler handler;//负责处理TCP接受到的消息
	private SocketConnector connector;//连接实体
	private ConnectThread connectThread;//处理连接的线程
	private DataReadThread dataReadThread;//处理回掉信息的线程
	private DataWriteThread dataWriteThread;//处理发送信息的线程
	private ControllerFactory controllerFactory;
	private ViewFactory viewFactory = new ViewFactory(this);
	private ServiceFactory serviceFactory = new ServiceFactory(this);
	private ManageFactory manageFactory = new ManageFactory(this);
	private SendHandlerFactory sendHandlerFactory = new SendHandlerFactory(this);
	private HeadPulseThread headPulseThread = new HeadPulseThread();//处理头像跳动的线程
	private QueueTaskThread queueTaskThread = new QueueTaskThread();//用于执行一些耗时的线程任务
	private boolean login = false;//用来标识是否已经成功登录了

	public AppContext() {
		long time = System.currentTimeMillis();
		initApp();
		System.out.println("initApp" + (System.currentTimeMillis() - time));
		initAction();
		System.out.println("initAction" + (System.currentTimeMillis() - time));
	}

	/**
	 * 初始化各个模块
	 */
	private void initApp() {

		controllerFactory = new ControllerFactory(this);
		handler = new Handler();
		connector = new SocketConnector(handler);

		dataWriteThread = new DataWriteThread();// 数据发送线程
		dataReadThread = new DataReadThread();//负责消息回掉的线程
		connectThread = new ConnectThread();

		connectThread.setConnector(connector);

		handler.setControllerFactory(controllerFactory);
		handler.addMessageHandler(new MessageHandler() {

			@Override
			public void setBackTime(long backTime) {
				dataWriteThread.setBackTime(backTime);
			}

			@Override
			public void back(Data data) {
				dataReadThread.back(data);
			}

			@Override
			public void addExceptionData(Data data) {
				dataReadThread.addExceptionData(data);
			}
		});

	}

	/**
	 * 初始化各个模块的
	 */
	private void initAction() {
		connectThread.addConnectBackAction(new ConnectBackAction() {

			@Override
			public void connectBack(boolean success) {
				if (logger.isDebugEnabled()) {
					String message = success ? "连接成功。" : "连接失败！";
					logger.debug(message);
				}
			}
		});
		/*** 为处理连接的线程添加连接状态变化的action，当连接断开了或者连接成功触发 ***/
		connectThread.addConnectStatusAction(new ConnectStatusAction() {

			@Override
			public void connectStatusChange(boolean isConnected) {
				dataReadThread.setLost(!isConnected);//当连接断开了，将 已发送信息标记为失败，让处理读信息的线程处理
				if (isConnected && login) {//如果是已经登录了，然后断网了，恢复网络后再次成功连接，那么进行自动重连接
					PersonalHandler ph = getHandler(PersonalHandler.class);
					ph.reconnect();
				}
				if (logger.isDebugEnabled()) {
					logger.debug("状态：" + isConnected);
				}
			}
		});
		dataWriteThread.addDataSentAction(new DataSentAction() {

			@Override
			public void execute(HandlerData handlerData) {
				dataReadThread.add(handlerData);//如果发送的消息有回掉处理，那么将其添加到回掉处理线程的队列
				if (logger.isDebugEnabled()) {
					logger.debug("发送消息：" + handlerData.getKey());
				}
			}
		});
		dataWriteThread.addWriteHandler(new WriteHandler() {

			@Override
			public void write(Data o) {

				if (connector.isConnected()) {
					connector.write(o);
				} else {
					dataReadThread.addExceptionData(o);
				}
			}
		});
	}

	/**
	 * 启动各个线程
	 */
	public void start() {
		connectThread.start();
		dataWriteThread.start();
		dataReadThread.start();
		headPulseThread.start();
	}

	public ConnectThread getConnectThread() {
		return connectThread;
	}

	public DataReadThread getDataReadThread() {
		return dataReadThread;
	}

	public DataWriteThread getDataWriteThread() {
		return dataWriteThread;
	}

	public HeadPulseThread getHeadPulseThread() {
		return headPulseThread;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public <T> T getSingleView(Class<? extends AbstractView> classType) {
		return viewFactory.getSingleView(classType);
	}

	public <T> T getService(Class<? extends Service> classType) {
		return serviceFactory.getService(classType);
	}

	public <T> T getManage(Class<? extends Manage> classType) {
		return manageFactory.getManage(classType);
	}

	public <T> T getHandler(Class<? extends SendHandler> classType) {
		return sendHandlerFactory.getHandler(classType);
	}

	/**
	 * 发送信息
	 * 
	 * @param data :信息
	 */
	public void write(Data data) {
		this.write(data, null);
	}

	/**
	 * 发送信息
	 * 
	 * @param data :信息
	 * @param dataBackAction :回调Action
	 */
	public void write(Data data, DataBackAction dataBackAction) {
		dataWriteThread.write(data, dataBackAction);
	}

	public void exit() {
		connectThread.closeConnect();
		System.exit(0);
	}

	/**
	 * 执行任务
	 * 
	 * @param executeTask
	 */
	public void add(ExecuteTask executeTask) {
		queueTaskThread.add(executeTask);
	}

	public static void main(String[] age) {
		AppContext appContext = new AppContext();
		LoginView loginView = appContext.getSingleView(LoginView.class);
		System.out.println(loginView);
	}
}
