package com.oim.business.controller;

import org.springframework.stereotype.Controller;

import com.oim.app.AppContext;
import com.oim.common.annotation.ActionMapping;
import com.oim.common.annotation.MethodMapping;
import com.oim.common.app.controller.AbstractController;
import com.oim.net.message.Message;

/**
 * 描述：
 * 
 * @author 夏辉
 * @date 2014年6月14日 下午9:31:55
 * @version 0.0.1
 */
@Controller
@ActionMapping(value = "001")
public class AppController extends AbstractController {

	public AppController(AppContext appContext) {
		super(appContext);
	}

	@MethodMapping(value = "0001")
	public void beat(Message message) {
		
	}
}
