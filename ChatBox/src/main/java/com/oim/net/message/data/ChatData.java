package com.oim.net.message.data;

import java.util.List;

/**
 * @author XiaHui
 * @date 2015年3月2日 下午2:51:24
 */
public class ChatData {

	private boolean underline = false;
	private boolean bold = false;
	private String color = "";
	private boolean italic = false;
	private String fontName = "微软雅黑";
	private int fontSize = 12;
	private List<ChatValueData> chatValueDataList;

	public boolean isUnderline() {
		return underline;
	}

	public void setUnderline(boolean underline) {
		this.underline = underline;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public List<ChatValueData> getChatValueDataList() {
		return chatValueDataList;
	}

	public void setChatValueDataList(List<ChatValueData> chatValueDataList) {
		this.chatValueDataList = chatValueDataList;
	}

}
