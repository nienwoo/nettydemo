package com.oim.ui.view;

import com.oim.app.AppContext;
import com.oim.common.app.view.AbstractView;
import com.oim.ui.VideoFrame;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class VideoView extends AbstractView {
	
	VideoFrame videoFrame = new VideoFrame();

	public VideoView(AppContext appContext) {
		super(appContext);

	}

	public void setVisible(boolean visible) {
		videoFrame.setVisible(visible);
	}

}
