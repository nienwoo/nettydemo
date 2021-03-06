/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import com.oim.bean.User;
import com.oim.common.box.ImageBox;
import com.oim.net.http.HttpHandler;
import com.oim.net.message.UserMessage;
import com.oim.ui.component.BaseDialog;
import com.only.OnlyButton;
import com.only.OnlyCalendarComboBox;
import com.only.OnlyLabel;
import com.only.OnlyPanel;
import com.only.OnlyPasswordField;
import com.only.OnlyRadioButton;
import com.only.OnlyTextField;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * 
 * @author XiaHui
 */
@SuppressWarnings("serial")
public class RegisterDialog extends BaseDialog {

	OnlyButton registerButton = new OnlyButton();
	OnlyButton saveButton = new OnlyButton();
	OnlyButton backToLoginButton = new OnlyButton();

	OnlyLabel nicknameLabel = new OnlyLabel();
	OnlyLabel passwordLabel = new OnlyLabel();
	OnlyLabel verifyPasswordLabel = new OnlyLabel();
	OnlyLabel genderLabel = new OnlyLabel();
	OnlyLabel birthdayLabel = new OnlyLabel();
	OnlyLabel addressLabel = new OnlyLabel();
	OnlyTextField addressTextField = new OnlyTextField();
	OnlyTextField nicknameTextField = new OnlyTextField();
	OnlyPasswordField passwordField = new OnlyPasswordField();
	OnlyPasswordField verifyPasswordField = new OnlyPasswordField();
	OnlyRadioButton manRadioButton = new OnlyRadioButton();
	OnlyRadioButton womanRadioButton = new OnlyRadioButton();
	OnlyCalendarComboBox birthdayCalendarComboBox = new OnlyCalendarComboBox();

	OnlyPanel panel = new OnlyPanel();
	ButtonGroup buttonGroup = new ButtonGroup();

	private OnlyButton agreeButton = new OnlyButton();
	private OnlyButton cancelButton = new OnlyButton();
	OnlyLabel waitingLabel = new OnlyLabel();

	ShowAccountDialog showAccountDialog = new ShowAccountDialog(this, "账号信息", true);

	public RegisterDialog(JFrame parent, String text) {
		this(parent, text, true);
		initComponents();
	}

	public RegisterDialog(JFrame frame, String text, boolean b) {
		super(frame, text, b);
		initComponents();
		initEvent();
	}

	private void initComponents() {
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(300, 320);
		this.setShowIconImage(false);
		this.setTitle("用户注册");
		this.getRootPane().setBackground(new Color(0, 128, 157));

		panel.setLayout(null);
		panel.setBackground(new Color(69, 159, 134));
		panel.setBounds(0, 40, 300, 280);

		buttonGroup.add(manRadioButton);
		buttonGroup.add(womanRadioButton);

		saveButton.setText("保存为");
		backToLoginButton.setText("返回登录");

		nicknameLabel.setText("昵称：");
		passwordLabel.setText("密码：");
		verifyPasswordLabel.setText("确认密码：");
		genderLabel.setText("性别：");
		birthdayLabel.setText("生日：");
		addressLabel.setText("所在地：");

		manRadioButton.setText("男");
		womanRadioButton.setText("女");

		agreeButton.setText("注册");
		cancelButton.setText("取消");
		Color white= new Color(255, 255, 255);
		
		
		nicknameLabel.setForeground(white);
		passwordLabel.setForeground(white);
		verifyPasswordLabel.setForeground(white);
		genderLabel.setForeground(white);
		birthdayLabel.setForeground(white);
		addressLabel.setForeground(white);

		manRadioButton.setForeground(white);
		womanRadioButton.setForeground(white);

		agreeButton.setForeground(white);
		cancelButton.setForeground(white);
		
		manRadioButton.setSelected(true);

		nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		verifyPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		genderLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		birthdayLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		addressLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

		nicknameLabel.setBounds(20, 20, 60, 25);
		nicknameTextField.setBounds(80, 20, 180, 25);

		passwordLabel.setBounds(20, 55, 60, 25);
		passwordField.setBounds(80, 55, 180, 25);

		verifyPasswordLabel.setBounds(20, 90, 60, 25);
		verifyPasswordField.setBounds(80, 90, 180, 25);

		genderLabel.setBounds(20, 125, 60, 25);
		manRadioButton.setBounds(80, 125, 50, 25);
		womanRadioButton.setBounds(130, 125, 50, 25);

		birthdayLabel.setBounds(20, 160, 60, 25);
		birthdayCalendarComboBox.setBounds(80, 160, 180, 25);

		addressLabel.setBounds(20, 195, 60, 25);
		addressTextField.setBounds(80, 195, 180, 25);

		cancelButton.setBounds(20, 235, 80, 30);
		agreeButton.setBounds(200, 235, 80, 30);

		panel.add(nicknameLabel);
		panel.add(passwordLabel);
		panel.add(verifyPasswordLabel);
		panel.add(genderLabel);

		panel.add(birthdayLabel);
		panel.add(addressLabel);
		panel.add(addressTextField);
		panel.add(nicknameTextField);
		panel.add(passwordField);
		panel.add(verifyPasswordField);
		panel.add(manRadioButton);
		panel.add(womanRadioButton);
		panel.add(birthdayCalendarComboBox);

		panel.add(agreeButton);
		panel.add(cancelButton);

		panel.add(waitingLabel);

		add(panel);

		waitingLabel.setBounds(0, 235, 300, 30);
		waitingLabel.setVisible(false);
		waitingLabel.setIcon(ImageBox.getIcon("Resources/Images/Default/Loading/loading_312_4.gif"));
	}

	private void initEvent() {
		agreeButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerActionPerformed(evt);
			}
		});

		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		this.setVisible(false);
	}

	private void registerActionPerformed(java.awt.event.ActionEvent evt) {
		reg();
	}

	private void reg() {

		String nickname = this.nicknameTextField.getText();
		String password = new String(passwordField.getPassword());
		String verifyPassword = new String(verifyPasswordField.getPassword());
		String gender = (manRadioButton.isSelected()) ? "男" : "女";
		String birthdate = birthdayCalendarComboBox.getDateString();
		String address = addressTextField.getText();

		if (null == nickname || "".equals(nickname)) {
			showMessage(nicknameTextField, nicknameTextField.getWidth(), 0, "昵称不能空！");
			return;
		}
		if ("".equals(password)) {
			showMessage(passwordField, passwordField.getWidth(), 0, "请输入密码！");
			return;
		}

		if ("".equals(verifyPassword)) {
			showMessage(verifyPasswordField, verifyPasswordField.getWidth(), 0, "请再次输入密码！");
			return;
		}

		if (!password.equals(verifyPassword)) {
			showMessage(verifyPasswordField, verifyPasswordField.getWidth(), 0, "两次密码不一致！");
			return;
		}

		UserMessage message = new UserMessage();
		message.setController("101");
		message.setMethod("0001");

		User user = new User();

		user.setPassword(password);
		user.setNickname(nickname);
		user.setGender(gender);
		user.setBirthdate(birthdate);
		user.setHomeAddress(address);

		message.setUser(user);
		showWaiting(true);
		DataBackActionAdapter dataBackAction = new DataBackActionAdapter() {

			@Override
			public void lostExecute(Data data) {
				showWaiting(false);
				RegisterDialog.this.showMessage(RegisterDialog.this,75,150,"注册失败，\n请检查网络连接是否正常。");
			}

			@Override
			public void backExecute(Data data) {
				showWaiting(false);
				handle(data);
			}
		};
		Runnable runnable = new Runnable() {
			Data data;
			DataBackAction dataBackAction;

			public Runnable execute(Data data, DataBackAction dataBackAction) {
				this.data = data;
				this.dataBackAction = dataBackAction;
				return this;
			}

			public void run() {
				new HttpHandler().execute(data, dataBackAction, UserMessage.class);
			}
		}.execute(message, dataBackAction);
		new Thread(runnable).start();

	}

	public void showWaiting(boolean show) {
		waitingLabel.setVisible(show);
		agreeButton.setVisible(!show);
		cancelButton.setVisible(!show);
	}

	private void handle(Data data) {
		if (data instanceof UserMessage) {
			UserMessage message = (UserMessage) data;
			if (UserMessage.result_code_true.equals(message.getResultCode())) {
				User user = message.getUser();
				String text = "注册成功，你的账号为：" + user.getAccount();
				showAccountDialog.setText(text);
				showAccountDialog.setVisible(true);
				clear();
			} else {
				this.showPromptMessage("注册失败!");
			}
		} else {
			this.showPromptMessage("注册失败!");
		}
	}

	public void clear() {
		this.nicknameTextField.setText("");
		passwordField.setText("");
		verifyPasswordField.setText("");

		birthdayCalendarComboBox.setSelectedItem("");
		addressTextField.setText("");
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/*
		 * Create and display the dialog
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				RegisterDialog dialog = new RegisterDialog(new JFrame(), "用户注册");
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
}
