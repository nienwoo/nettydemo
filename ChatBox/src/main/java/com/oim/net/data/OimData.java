package com.oim.net.data;

import com.only.net.data.Data;

/**
 * 描述：
 * 
 * @author 夏辉
 * @date 2014年6月14日 下午10:19:24
 * @version 0.0.1
 */
public interface OimData extends Data {

	public static final String result_code_true = "0";
	public static final String result_code_false = "1";
	
	String getKey();

	void setKey(String key);

	String getName();

	void setName(String name);

	String getVersion();

	void setVersion(String version);

	int getClientType();

	void setClientType(int clientType);

	String getController();

	void setController(String controller);

	String getMethod();

	void setMethod(String method);

	String getResultCode();

	void setResultCode(String resultCode);

	String getResultMessage();

	void setResultMessage(String resultMessage);
}
