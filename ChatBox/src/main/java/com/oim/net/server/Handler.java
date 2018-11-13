/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.net.server;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.oim.common.app.controller.ControllerFactory;
import com.oim.common.util.ClassScaner;
import com.oim.common.util.OnlyStringUtil;
import com.oim.net.connect.MessageHandler;
import com.oim.net.message.Message;
import com.only.net.data.Data;
import com.only.xml.XmlTools;

/**
 * date 2012-9-14 17:26:16
 * 
 * @author XiaHui
 */
public class Handler extends IoHandlerAdapter {

	protected final Logger logger = Logger.getLogger(this.getClass().getName());
	private XmlTools xmlTools = new XmlTools();
	ControllerFactory controllerFactory;
	Set<MessageHandler> messageHandlerSet = new HashSet<MessageHandler>();

	public Handler() {
		init();
	}

	private void init() {
		ClassScaner cs = new ClassScaner();
		Set<Class<?>> classSet = cs.doScan("com.oim.net.message");
		for (Class<?> classType : classSet) {
			xmlTools.add(classType);
		}
	}

	public void addMessageHandler(MessageHandler messageHandler) {
		messageHandlerSet.add(messageHandler);
	}

	public void setControllerFactory(ControllerFactory controllerFactory) {
		this.controllerFactory = controllerFactory;
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.debug("客户端与服务端连接打开.....");
	}

	@Override
	public void sessionClosed(IoSession session) {
		session = null;
		logger.debug("客户端与服务端断开连接.....");
	}

	@Override
	public void messageSent(IoSession session, Object object) throws Exception {
		logger.debug("客户端已经向服务器发送了消息.....");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable throwable) throws Exception {
		Data data = (Data) session.getAttribute(Data.class);
		addExceptionData(data);
		if (null != throwable) {
			String ioException = OnlyStringUtil.exceptionToString(throwable);
			String exception1 = "org.apache.mina.transport.socket.nio.NioProcessor.read";
			String exception2 = "org.apache.mina.core.polling.AbstractPollingIoProcessor.read";
			String exception3 = "org.apache.mina.core.polling.AbstractPollingIoProcessor.process";
			if (-1 == ioException.indexOf(exception1) && -1 == ioException.indexOf(exception2) && -1 == ioException.indexOf(exception3)) {
				logger.error("客户端发送信息异常", throwable);
			} else {
				String message = "服务器断开！！！";
				logger.error(message);
			}
		} else {
			logger.error("客户端发送信息异常", throwable);
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.debug("客户端与服务端创建连接.....");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// SessionThread.idleStatus = true;
		logger.debug(status);
	}

	// 客户端接收到的消息为：
	@Override
	public void messageReceived(IoSession session, Object object) throws Exception {
		try {
			back();
			Object request = null;
			if (object instanceof String) {
				request = xmlTools.xmlNodeToObject((String) object);
			}

			if (request instanceof Message) {
				Message data = (Message) request;
				back(data);
				String classCode = data.getController();
				String methodCode = data.getMethod();

				if (null != controllerFactory && StringUtils.isNotBlank(classCode) && StringUtils.isNotBlank(methodCode)) {
					Object controller = controllerFactory.getController(classCode);
					Method method = controllerFactory.getMethod(classCode, methodCode);
					if ((null != controller && null != method)) {
						Object result = method.invoke(controller, data);
						if (null != result) {
							String xml = xmlTools.objectToXmlNode(result);
							session.write(xml);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	void back() {

		for (MessageHandler messageHandler : messageHandlerSet) {
			messageHandler.setBackTime(System.currentTimeMillis());
		}

	}

	void back(Data data) {
		for (MessageHandler messageHandler : messageHandlerSet) {
			messageHandler.back(data);
		}
	}

	void addExceptionData(Data data) {
		for (MessageHandler messageHandler : messageHandlerSet) {
			messageHandler.addExceptionData(data);
		}
	}
}
