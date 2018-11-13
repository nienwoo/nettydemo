package com.oim.net.message.data;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月24日 下午12:30:06
 * @version 0.0.1
 */
public class ChatValueData {

	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static final String type_text = "text";
	public static final String type_image = "image";
	public static final String type_face = "face";
}
