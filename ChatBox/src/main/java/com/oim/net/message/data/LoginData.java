package com.oim.net.message.data;

/**
 * @Author: XiaHui
 * @Date: 2016年1月5日
 * @ModifyUser: XiaHui
 * @ModifyDate: 2016年1月5日
 */
public class LoginData {

	private int number;// 数字帐号
	private String account;// 帐号
	private String email;// 电子信箱
	private String mobilePhone;// 手机
	private String password;// 密码
	private int status;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
