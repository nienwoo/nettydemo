package com.oim.net.http;

import org.apache.commons.lang3.StringUtils;

import com.oim.common.config.ConfigManage;
import com.oim.common.config.data.ConnectConfigData;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.xml.XmlTools;

/**
 * @author XiaHui
 * @date 2015年3月4日 下午5:47:32
 */
public class HttpHandler {

	private HttpClientHandler httpClientHandler = new HttpClientHandler();
	private XmlTools xmlTools = new XmlTools();
	String url = "http://127.0.0.1:8080/";

	public void execute(Data data, DataBackAction dataBackAction, Class<? extends Data> backType) {
		ConnectConfigData ccd = (ConnectConfigData) ConfigManage.get(ConnectConfigData.path, ConnectConfigData.class);
		try {
			StringBuilder url = new StringBuilder();
			url.append("http://");
			url.append(ccd.getBusinessAddress());
			url.append(":");
			url.append(ccd.getHttpPort());
			url.append("/");

			String xml = xmlTools.objectToXmlNode(data);
			String r = httpClientHandler.post(url.toString(), xml);

			if (StringUtils.isNotBlank(r) && null != dataBackAction) {
				Data o = xmlTools.xmlNodeToObject(r, backType);
				dataBackAction.backExecute(o);
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
			if (null != dataBackAction) {
				dataBackAction.lostExecute(data);
			}
		}

	}

	public static void main(String main[]) {
		new HttpHandler().execute(null, null, null);
	}
}
