package com.oim.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.oim.common.util.SystemUtil;
import com.oim.ui.video.CameraVideoPanel;
import com.oim.ui.video.VideoShowPanel;

public class VideoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel basePanel = new JPanel();
	JPanel buttonPanel = new JPanel(new FlowLayout());
	JPanel tabPanel = new JPanel();
	JPanel ownPanel = new JPanel();
	JLabel ownLabel = new JLabel();
	JTabbedPane tabbedPane = new JTabbedPane();

	CameraVideoPanel cameraVideoPanel = new CameraVideoPanel();

	public VideoFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("视频聊天");
		this.setSize(482, 665);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.add(basePanel);
		this.add(buttonPanel);
		initUI();
	}

	private void initUI() {
		String dllpath = "dll";
		SystemUtil.addLibPath(dllpath);
		tabPanel.setLayout(new CardLayout());
		tabPanel.add(tabbedPane);

		basePanel.setBounds(0, 0, 480, 600);
		buttonPanel.setBounds(0, 600, 480, 65);

		basePanel.setLayout(null);
		basePanel.add(tabPanel);
		basePanel.add(ownPanel);
		basePanel.setBackground(new Color(125, 155, 52));

		tabPanel.setBounds(0, 0, 480, 330);
		

		ownPanel.setBounds(0, 330, 480, 270);
		ownPanel.setBackground(Color.red);
		ownPanel.setLayout(new CardLayout());
		ownPanel.add(ownLabel);

		JButton selectButton = new JButton("切换摄像头");
		buttonPanel.add(selectButton);
		selectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cameraVideoPanel.chooseDevice(VideoFrame.this);
			}
		});
		cameraVideoPanel.setPreferredSize(new Dimension(800, 450));
		
		cameraVideoPanel.setBounds(0, 0, 800, 450);
	}

	public void stopVideo() {
		if (cameraVideoPanel.isStart()) {
			cameraVideoPanel.stopVideo();
		}
	}

	public void startVideo() {
		if (!cameraVideoPanel.isStart()) {
			cameraVideoPanel.startVideo();
		}
	}

	public boolean isStartVideo() {
		return cameraVideoPanel.isStart();
	}

	public void addTab(String title, Component component) {
		tabbedPane.addTab(title, component);
	}

	public void removeTab(Component component) {
		int index = tabbedPane.indexOfComponent(component);
		if (-1 != index) {
			tabbedPane.removeTabAt(index);
		}
	}

	public void removeAllTab() {
		tabbedPane.removeAll();
	}
	
	public boolean isTabEmpty(){
		return tabbedPane.getComponentCount()==0;
	}

	 
	public BufferedImage getVideoImage() {
		BufferedImage image = null;
		if (cameraVideoPanel.isStart()) {
			try {
				image = new BufferedImage(800,450, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = image.createGraphics();
				cameraVideoPanel.paint(g);
				g.dispose();
				
				Image m=image;
				m = m.getScaledInstance(480, 320, Image.SCALE_SMOOTH);
				ownLabel.setIcon(new ImageIcon(m));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return image;
	}

	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				VideoFrame camera = new VideoFrame();
				camera.setVisible(true);
				VideoShowPanel vsp = new VideoShowPanel();
				camera.addTab("账号", vsp);
				camera.addTab("002", vsp);
			}
		});
	}
}