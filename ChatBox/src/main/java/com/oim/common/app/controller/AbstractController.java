package com.oim.common.app.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.oim.app.AppContext;
import com.oim.common.app.view.AbstractView;


/**
 * @description:
 * @author XiaHui
 * @date 2014年7月2日 下午12:01:19
 * @version 1.0.0
 */
public abstract class AbstractController {

	protected Map<String, Method> methodMap = new HashMap<String, Method>();
	protected AppContext appContext;

	public AbstractController(AppContext appContext) {
		this.appContext = appContext;
		initMethodMap();
	}

	/**
	 * 初始化保存当前类所有方法是map
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	protected Map initMethodMap() {
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			methodMap.put(method.getName(), method);
		}
		Class superClass = this.getClass().getSuperclass();
		if (null != superClass) {
			getMethodMap(superClass);
		}
		return methodMap;
	}

	@SuppressWarnings({ "rawtypes" })
	protected void getMethodMap(Class className) {
		Method[] methods = className.getDeclaredMethods();
		for (Method method : methods) {
			methodMap.put(method.getName(), method);
		}
		Class superClass = className.getSuperclass();
		if (null != superClass) {
			getMethodMap(superClass);
		}
	}

	public Method getMethod(String methodName) {
		return methodMap.get(methodName);
	}

	public <T> T getSingleView(Class<? extends AbstractView> classType) {
		return appContext.getSingleView(classType);
	}

}
