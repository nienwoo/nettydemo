package com.oim.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.oim.app.AppContext;
import com.oim.bean.User;
import com.oim.business.handler.PersonalHandler;
import com.oim.common.app.view.AbstractView;
import com.oim.common.box.PersonalBox;
import com.oim.common.util.BeanUtils;
import com.oim.net.message.UserMessage;
import com.oim.ui.UpdatePasswordDialog;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年2月10日 下午4:34:09
 * @version 0.0.1
 */
public class UpdatePasswordView extends AbstractView {
	UpdatePasswordDialog upd = new UpdatePasswordDialog(new javax.swing.JFrame(), "密码修改", true);

	public UpdatePasswordView(AppContext appContext) {
		super(appContext);
		initEvent();
	}

	private void initEvent() {
		upd.addSaveAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updatePassword();
			}
		});
	}

	public void setVisible(boolean visible) {
		upd.setVisible(visible);
	}

	private void updatePassword() {
		String oldPassword = upd.getOldPassword();
		String newPassword = upd.getNewPassword();
		String verifyPassword = upd.getVerifyPassword();

		if (null == oldPassword || "".equals(oldPassword.trim())) {
			upd.showPromptMessage("请输入原来的密码！");
			return;
		}
		if (null == newPassword || "".equals(newPassword.trim())) {
			upd.showPromptMessage("请输新密码！");
			return;
		}
		if (null == verifyPassword || "".equals(verifyPassword.trim())) {
			upd.showPromptMessage("请再一次输入新密码！");
			return;
		}
		User user = PersonalBox.get(User.class);
		if (!oldPassword.equals(user.getPassword())) {
			upd.showPromptMessage("旧密码不正确！");
			return;
		}
		if (!newPassword.equals(verifyPassword.trim())) {
			upd.showPromptMessage("两次输入密码不一致！");
			return;
		}
		User u=new User();
		BeanUtils.copyProperties(u, user);
		u.setPassword(newPassword);
		DataBackActionAdapter action = new DataBackActionAdapter() {// 这是消息发送后回掉
			@Override
			public void lostExecute(Data data) {
				upd.showPromptMessage("修改失败。");
			}

			@Override
			public void timeOutExecute(Data data) {
				upd.showPromptMessage("修改失败。");
			}
			@Override
			public void backExecute(Data data) {

				if (data instanceof UserMessage) {
					UserMessage message = (UserMessage) data;
					if (UserMessage.result_code_true.equals(message.getResultCode())) {
						upd.setVisible(false);
						MainView mv=appContext.getSingleView(MainView.class);
						mv.showPromptMessage("修改成功。");
						User u=message.getUser();
						PersonalBox.put(User.class, u);
					} else {
						upd.showPromptMessage("修改失败。");
					}
				}
			}
		};
		PersonalHandler ph = this.appContext.getHandler(PersonalHandler.class);
		ph.upadtePassword(u,action);
	}
}
