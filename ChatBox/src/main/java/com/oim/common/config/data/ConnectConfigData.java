/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.common.config.data;

/**
 * 2013-9-6 16:27:40
 * 保存连接服务器的地址信息
 * @author XiaHui
 */
public class ConnectConfigData {

	public static final String path = "Resources/Config/ConnectConfigData.xml";
	private String businessAddress = "127.0.0.1";
	private int businessPort = 12000;
	private int httpPort = 8080;
	private int fileServerPort = 12020;

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public int getBusinessPort() {
		return businessPort;
	}

	public void setBusinessPort(int businessPort) {
		this.businessPort = businessPort;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public int getFileServerPort() {
		return fileServerPort;
	}

	public void setFileServerPort(int fileServerPort) {
		this.fileServerPort = fileServerPort;
	}

}
