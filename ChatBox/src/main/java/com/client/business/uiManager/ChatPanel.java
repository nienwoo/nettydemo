package com.client.business.uiManager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.client.chat.Gain_Voice;
import com.client.chat.PicsJWindow;
import com.client.chat.Play_Voice;
import com.client.cut.Cut;
import com.client.data.ClientToServer;
import com.client.data.LoadImages;
import com.client.qipao.BubbleModel;
import com.client.qipao.BubbleRenderer;
import com.client.qipao.IMMessage;
import com.client.transform.ServerTest;
import com.client.view.JChatFrm;
import com.client.view.MyButton;
import com.common.Message;
import com.common.Port;
import com.common.ReceivePort;
import com.common.UserInfoBean;
import com.tools.ClientToServerThread;

public class ChatPanel extends JPanel implements ActionListener, ItemListener

{
    String image_ep_number = null;
    ImageIcon cut_image = null;
    int voice_flag = 0;

    // ������Ϣ
    JTable table = new JTable();
    JScrollBar jsbar;
    BubbleModel mModel = new BubbleModel();
    String sSend = "1";
    int number = 0;
    public MyButton btn_qp;// ����
    public MyButton btn_wz;// ����
    boolean qp_flag = false;// ��ǰ��������ģʽ
    // ������Ϣ
    private ImageIcon send_Image;// ���ͱ���
    private int image_number = 0;
    private boolean i_flag = false;
    private Point start;
    private Timer shakeTimer;
    private int i_num = 0;

    private PicsJWindow picWindow;


    Gain_Voice gainvoice;  // ��ȡ��Ƶ����
    Play_Voice playvoice;  // ��������
    Socket gainsocket;     // ��Ƶ���յ�Socket

    MyButton icon_btn;
    JChatFrm chatfrm;
    String backgroud = "bk";
    MessageLoggingPanel messageloggingpanel = new MessageLoggingPanel();
    boolean b_ml = false; // ��Ϣ��¼

    public JPanel font_panel; // �����������
    public JPanel expression_panel;// �������
    public JComboBox cb_font; // ����

    public JComboBox cb_size; // �ֵĴ�С
    public MyButton bt_bold; // ����
    public boolean b_bold = true;
    public MyButton bt_Italic; // б��
    public boolean b_Italic = false;
    public MyButton bt_Underline; // �»���
    public boolean b_Underline = false;
    public MyButton bt_color; // �ֵ���ɫ
    public boolean b_color = false;

    public JPanel panel2;

    public MyButton btn_head_portrait; // ͷ��
    public JLabel lab_nickname; // �ǳ�
    public JLabel lab_autograph; // ǩ��
    public MyButton bt_video; // ��������Ƶ�Ի�
    public MyButton bt_voice; // ���������Ի�
    public MyButton bt_voice_cancel;// ����ȡ��
    public MyButton bt_voice_accept;// ����������Ϣ
    public MyButton bt_voice_deny;// �ܾ�������Ϣ
    public MyButton bt_file; // �����ļ�
    public MyButton bt_file_cancel;// ȡ�������ļ�
    public MyButton bt_file_accept;// �����ļ�
    public JLabel lb_file_name;// jieshouwenjianming
    public MyButton bt_file_save;
    public MyButton bt_file_deny;// �ܾ������ļ�
    public MyButton bt_create_group; // ����Ⱥ��
    public MyButton bt_app; // Ӧ��

    public ImageIcon icon;
    public LoadImages image;

    public MyTextPane tp_message = new MyTextPane();
    public JScrollPane spanel1;

    public MyButton bt_font; // ����
    public boolean b_font = false;//
    public MyButton bt_erpression; // ����
    public MyButton btn_shanke;// ��
    public MyButton bt_voice_message; // ������Ϣ
    public MyButton bt_Screen;// ����
    public MyButton bt_image; // ����ͼƬ
    public MyButton bt_message_logging;// ��Ϣ��¼
    public JScrollPane spanel2;// ������ϢPanel����
    public MyTextPane tp_send_message = new MyTextPane();
    public MyButton bt_close; // �ر�
    public MyButton bt_send; // ������Ϣ
    public String send_font = "��������"; // ������Ϣ������
    public boolean send_font_bold; // ������Ϣ������
    public int send_font_size = 20; // ������Ϣ���ֵĴ�С
    public Color send_color = Color.BLUE; // ������Ϣ����ɫ��

    private String begin;
    private String info;
    // ///////////////////////////////////
    ClientToServerThread ctsT;
    UserInfoBean user;
    UserInfoBean friend;
    Message message;
    private int sendPORT;
    private int receivePort;

    private boolean isStop = false;
    ClientToServer cts = null;

    public ChatPanel(JChatFrm chatfrm, ClientToServerThread ctsT,
                     UserInfoBean user, UserInfoBean friend)
    {

        this.chatfrm = chatfrm;
        this.friend = friend;
        getPropertiesInfo();
        this.user = user;
        send_Image = null;


        message = new Message();
        message.setSendIP(friend.getIP());
        message.setReceiveIP(user.getIP());
        message.setSendPort(sendPORT);
        message.setReceivePort(receivePort);
        message.setsendSign(user.getSign());
        message.setsendPhotoID(user.getPhotoID());

        message.setSendQq(user.getQq());
        message.setSendNickName(user.getNickName());
        message.setReceiveQq(friend.getQq());
        message.setReceiveNickName(friend.getNickName());
        message.setreceiveSign(friend.getSign());
        message.setreceivePhotoID(friend.getPhotoID());

        lab_nickname = new JLabel(friend.getNickName());
        lab_autograph = new JLabel(friend.getSign());

        ctsT = new ClientToServerThread(tp_message, this, friend.getQq(), receivePort);
        ctsT.start();
        this.ctsT = ctsT;

        setLayout(null);// ����null����

        icon = new ImageIcon();
        image = new LoadImages();

        setFontPanel();// ��������panel
        setMessage();// ������Ϣ

        {
            btn_head_portrait = new MyButton();// ͷ��
            btn_head_portrait.setBounds(5, 2, 45, 43);
            String face = "Image/MainIcon/qqicons\\Catch0000"
                    + friend.getPhotoID() + ".jpg";

            btn_head_portrait.setIcon(new ImageIcon(face));
            btn_head_portrait.setContentAreaFilled(false); // ��
            add(btn_head_portrait);

            lab_nickname.setFont(new Font("����", 1, 25));
            lab_nickname.setBounds(50, 8, 300, 20);
            add(lab_nickname);

            lab_autograph.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
            lab_autograph.setForeground(Color.BLUE);
            lab_autograph.setBounds(85, 24, 800, 30);

            add(lab_autograph);
        }
        {
            bt_voice = new MyButton();
            bt_voice.setBounds(4, 48, 42, 32);
            bt_voice.setToolTipText("�����Ự");
            bt_voice.addActionListener(this);
            add(bt_voice);

            bt_voice_cancel = new MyButton();
            bt_voice_cancel.setBounds(497, 273, 62, 27);
            bt_voice_cancel.addActionListener(this);
            bt_voice_cancel.setVisible(false);
            add(bt_voice_cancel);
            {
                bt_voice_accept = new MyButton();
                bt_voice_accept.setBounds(456, 280, 62, 27);
                bt_voice_accept.addActionListener(this);
                bt_voice_accept.setVisible(false);
                add(bt_voice_accept);

                bt_voice_deny = new MyButton();// �ܾ�����
                bt_voice_deny.setBounds(531, 280, 62, 27);
                bt_voice_deny.addActionListener(this);
                bt_voice_deny.setVisible(false);
                add(bt_voice_deny);
            }
            bt_video = new MyButton();// ��Ƶ�Ự
            bt_video.setBounds(55, 48, 42, 32);
            bt_video.setToolTipText("��Ƶ�Ự");
            add(bt_video);

            bt_file = new MyButton();//�ļ�����
            bt_file.setBounds(103, 48, 42, 32);
            bt_file.setToolTipText("�ļ�����");
            bt_file.addActionListener(this);
            add(bt_file);

            bt_file_accept = new MyButton();// �����ļ�
            bt_file_accept.setBounds(586, 157, 38, 23);
            bt_file_accept.addActionListener(this);
            bt_file_accept.setVisible(false);
            add(bt_file_accept);

            bt_file_save = new MyButton();// ȡ�������ļ�
            bt_file_save.setBounds(625, 157, 43, 23);
            bt_file_save.addActionListener(this);
            bt_file_save.setVisible(false);
            add(bt_file_save);

            bt_file_deny = new MyButton();// ȡ�������ļ�
            bt_file_deny.setBounds(672, 157, 38, 23);
            bt_file_deny.addActionListener(this);
            bt_file_deny.setVisible(false);
            add(bt_file_deny);

            bt_file_cancel = new MyButton();// ȡ�������ļ�
            bt_file_cancel.setBounds(692, 155, 36, 23);
            bt_file_cancel.addActionListener(this);
            bt_file_cancel.setVisible(false);
            add(bt_file_cancel);

            lb_file_name = new JLabel();//��ʾ�����ļ���
            lb_file_name.setFont(new Font("����", Font.PLAIN,

                    15));
            lb_file_name.setForeground(Color.black);
            lb_file_name.setBounds(512, 125, 4500, 20);
            lb_file_name.setText("");
            lb_file_name.setVisible(false);
            add(lb_file_name);

            bt_create_group = new MyButton();
            bt_create_group.setBounds(150, 48, 37, 32);
            bt_create_group.setToolTipText("����Ⱥ��");
            add(bt_create_group);

            bt_app = new MyButton();
            bt_app.setBounds(281, 48, 42, 32);
            bt_app.setToolTipText("Ӧ��");
            add(bt_app);
        }

        {

            bt_font = new MyButton();//��������
            bt_font.setBounds(1, 373, 27, 28);
            bt_font.setToolTipText("����");
            bt_font.addActionListener(this);
            add(bt_font);

            bt_erpression = new MyButton();//����
            bt_erpression.setBounds(28, 373, 27, 28);
            bt_erpression.setToolTipText("����");
            bt_erpression.addActionListener(this);
            add(bt_erpression);

            btn_shanke = new MyButton();//����
            btn_shanke.setBounds(77, 373, 28, 28);
            btn_shanke.setToolTipText("��");
            btn_shanke.addActionListener(this);
            add(btn_shanke);

            bt_voice_message = new MyButton();//������Ϣ
            bt_voice_message.setBounds(105, 373, 26, 28);
            bt_voice_message.setToolTipText("������Ϣ");
            bt_voice_message.addActionListener(this);
            add(bt_voice_message);

            bt_image = new MyButton();
            bt_image.setBounds(156, 373, 35, 28);
            bt_image.setToolTipText("����ͼƬ");
            bt_image.addActionListener(this);
            add(bt_image);

            bt_Screen = new MyButton();
            bt_Screen.setBounds(220, 373, 35, 28);
            bt_Screen.setToolTipText("����");
            bt_Screen.addActionListener(this);
            add(bt_Screen);

            bt_message_logging = new MyButton();
            bt_message_logging.setBounds(356, 373, 83, 27);
            bt_message_logging.setToolTipText("��Ϣ��¼");
            bt_message_logging.addActionListener(this);
            add(bt_message_logging);
        }

        setSendMessage();
        {
            bt_close = new MyButton();
            bt_close.setBounds(273, 476, 72, 28);
            bt_close.setContentAreaFilled(false);
            bt_close.setToolTipText("�ر�");
            add(bt_close);

            bt_send = new MyButton();
            bt_send.setBounds(347, 476, 92, 28);
            bt_send.setContentAreaFilled(false);
            bt_send.addActionListener(this);
            bt_send.setToolTipText("����");
            add(bt_send);
        }

        add(messageloggingpanel);
        this.repaint();
    }


    public void paintComponent(Graphics g)
    {
        drawBtnVideo(g);

    }

    public void drawBtnVideo(Graphics g)
    {
        LoadImages image = new LoadImages();
        ImageIcon icon = new ImageIcon();

        if (user.getQq() / 2 == 0)
        {
            backgroud += "0";
        }
        icon = image.LoadImageIcon(backgroud);
        g.drawImage(icon.getImage(), -1, -1, getSize().width + 3,
                getSize().height + 3, this);

    }

    /**
     * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub

        if (e.getSource() == bt_send)
        { // ������Ϣ
            sendMessage();
        }
        else if (e.getSource() == bt_font)
        { // ��������
            if (b_font == false)
            {
                font_panel.setVisible(true);
                icon = image.LoadImageIcon("font1");
                this.backgroud = "bk2";
                if (qp_flag)
                {

                    btn_qp.setVisible(false);
                    btn_wz.setVisible(true);
                }
                else
                {
                    btn_qp.setVisible(true);
                    btn_wz.setVisible(false);
                }
                spanel1.setBounds(0, 83, 447, 260);
                b_font = true;
            }
            else
            {

                btn_qp.setVisible(true);
                btn_wz.setVisible(true);
                font_panel.setVisible(false);
                this.backgroud = "bk";
                icon = image.LoadImageIcon("font0");
                spanel1.setBounds(0, 83, 447, 290);
                b_font = false;
            }
            bt_font.setIcon(icon);
        }
        else if (e.getSource() == bt_bold)
        {//����Ӵ�
            if (b_bold == false)
            {
                icon = image.LoadImageIcon("bold");
                b_bold = true;
            }
            else
            {

                icon = null;
                b_bold = false;
            }
            bt_bold.setIcon(icon);
        }
        else if (e.getSource() == bt_Italic)
        {//����б��
            if (b_Italic == false)
            {
                icon = image.LoadImageIcon("Italic");
                b_Italic = true;
            }
            else
            {

                icon = null;
                b_Italic = false;
            }
            bt_Italic.setIcon(icon);
        }
        else if (e.getSource() == bt_Underline)
        {//�����»���
            if (b_Underline == false)
            {
                icon = image.LoadImageIcon("Underline");
                b_Underline = true;
            }
            else
            {
                icon = null;
                b_Underline = false;
            }
            bt_Underline.setIcon(icon);

        }
        else if (e.getSource() == bt_color)
        {//������ɫ
            Color newColor = JColorChooser.showDialog(this, "������ɫ", send_color);
            send_color = newColor;
        }
        else if (e.getSource() == bt_message_logging)
        {
            // TODO:
            if (b_ml == false)
            {

                message.setInfoType("Record");
                ctsT.sendData(message);
                ctsT.sendData(message);

                // JOptionPane.showMessageDialog(null, "send ");
                chatfrm.setSize(809, 508);
                chatfrm.btn_close.setBounds(781, 1, 28, 26);
                this.backgroud = "bk1";
                messageloggingpanel.setOpaque(false);
                messageloggingpanel.setVisible(true);

                this.repaint();
                b_ml = true;
            }
            else
            {

                chatfrm.setSize(586, 508);
                chatfrm.btn_close.setBounds(558, 1, 28, 26);
                this.backgroud = "bk";
                this.repaint();
                messageloggingpanel.setVisible(false);
                b_ml = false;
            }

        }
        else if (e.getSource() == bt_image)
        {//����ͼƬ
            tp_send_message.insertImage(sendImage());
        }
        else if (e.getSource() == bt_file)
        {//�����ļ�
            System.out.println("fule");
//			JFileChooser chooser = new JFileChooser();
//			File file = null;
//			FileNameExtensionFilter filter = new FileNameExtensionFilter(
//					"�ı��ļ�", "txt", "doc");
//			chooser.setFileFilter(filter);
//			int option = chooser.showOpenDialog(this);
//			if (option == JFileChooser.APPROVE_OPTION) {
//				file = chooser.getSelectedFile();
//				try {
//					ImageIcon image = new ImageIcon(file.toURI().toURL());
//
//				} catch (MalformedURLException e1) {
//					e1.printStackTrace();
//				}
//			}

            message.setInfoType("File");
            message.setSendIP(getLocalIP());
            //	ctsT.sendData(message);
            //	ctsT.sendData(message);
            new ServerTest(this, message, ctsT);

            // bt_File_Accept();
        }
        else if (e.getSource() == bt_file_accept)
        {//�ļ�����
            lb_file_name.setVisible(true);
            this.backgroud = "file_send";
            chatfrm.setSize(730, 508);
            chatfrm.btn_close.setBounds(702, 1, 25, 26);
            bt_message_logging.setEnabled(false);
            bt_file_cancel.setVisible(true);
            bt_file_save.setVisible(false);
            bt_file_accept.setVisible(false);
            bt_file_deny.setVisible(false);
            lb_file_name.setText("wenjianming");//��ʾ���͵��ļ���
            lb_file_name.setVisible(true);
            this.repaint();

        }
        else if (e.getSource() == bt_file_save)
        {// �ļ��������Ϊ

        }
        else if (e.getSource() == bt_file_deny)
        {//�ļ�����ȡ��

            this.backgroud = "bk";
            chatfrm.setSize(586, 508);
            chatfrm.btn_close.setBounds(558, 1, 28, 26);

            lb_file_name.setVisible(false);
            bt_message_logging.setEnabled(true);
            bt_file_accept.setVisible(false);
            bt_file_save.setVisible(false);
            bt_file_deny.setVisible(false);

            lb_file_name.setVisible(false);
            this.repaint();
        }
        else if (e.getSource() == bt_file_cancel)
        {//�ļ�ȡ��
            this.backgroud = "bk";
            chatfrm.setSize(586, 508);
            chatfrm.btn_close.setBounds(558, 1, 28, 26);

            lb_file_name.setVisible(false);
            bt_message_logging.setEnabled(true);
            bt_file_cancel.setVisible(false);
            bt_file_deny.setVisible(false);

            lb_file_name.setVisible(false);
            this.repaint();
        }
        else if (e.getSource() == btn_shanke)
        {//���Ͷ���
            message.setInfoType("Shake");
            ctsT.sendData(message);
            ctsT.sendData(message);
            startShake();// ����Ϣ
        }
        else if (e.getSource() == bt_erpression)
        {//���ͱ���
            System.out.println("bioqing");
            if (i_flag == false)
            {
                chatfrm.Bt_erpression_true();
                i_flag = true;
            }
            else
            {
                chatfrm.Bt_erpression_false();
                i_flag = false;
            }
        }
        else if (e.getSource() == btn_qp)
        {//����ģʽ
            qp_flag = true;
            this.backgroud = "bk3";
            font_panel.setVisible(false);
            spanel1.setViewportView(table);
            btn_qp.setVisible(false);
            btn_wz.setVisible(true);
        }
        else if (e.getSource() == btn_wz)
        {//����ģʽ
            this.backgroud = "bk2";
            qp_flag = false;
            font_panel.setVisible(true);
            spanel1.setViewportView(tp_message);
            btn_qp.setVisible(true);
            btn_wz.setVisible(false);
        }
        else if (e.getSource() == bt_Screen)
        {
            new Cut(getChatPanel());//
        }
        else if (e.getSource() == bt_voice)//�����ʼͨ��
        {
            voice_flag++;
            Message message = new Message();
            message.setReceiveQq(friend.getQq());
            message.setsendPhotoID(user.getPhotoID());
            message.setSendQq(user.getQq());
            message.setsendSign(user.getSign());
            message.setInfoType("Voice");
            message.setSendIP(getLocalIP());
            ctsT.sendData(message);
            ctsT.sendData(message);

            //String Ip = "192.168.191.6";
            playvoice = new Play_Voice();
            playvoice.start();

            if (voice_flag == 2)
            {
                bt_Voice();
                voice_flag = 0;
            }

        }
        else if (e.getSource() == bt_voice_accept)// ��������ͨ��
        {


        }
        else if (e.getSource() == bt_voice_deny)// �ܾ�����ͨ��
        {
            this.backgroud = "bk";
            chatfrm.setSize(586, 508);
            bt_voice.setEnabled(true);
            bt_video.setEnabled(true);
            chatfrm.btn_close.setBounds(558, 1, 28, 26);
            bt_voice_cancel.setVisible(false);
            bt_message_logging.setEnabled(true);
            bt_voice_accept.setVisible(false);
            bt_voice_deny.setVisible(false);
            this.repaint();
        }
        else if (e.getSource() == bt_voice_cancel)
        {


            voice_cancel();
            message.setInfoType("CloseVoice");
            ctsT.sendData(message);
            ctsT.sendData(message);

        }

        tp_send_message.removeAll();
        String str1 = tp_send_message.getText().trim();
        tp_send_message.setText(" ");
        if (str1.length() == 0)
        {
            str1 = " ";
        }
        tp_send_message.setDocs(str1, send_color, send_font, b_bold, b_Italic,
                b_Underline, send_font_size);
    }

    //¼��
    public void startYuyin(String Ip)
    {


        //	show_voice_accept();
        // bt_Voice();

        // ��������

        gainvoice = new Gain_Voice(Ip); // ��ʼ��һ������


        gainvoice.start();
        //gainvoice.run(); // ��ȡ��Ƶ�豸������

        this.backgroud = "voiceing";
        bt_voice_accept.setVisible(false);
        bt_voice_deny.setVisible(false);
        bt_voice_cancel.setBounds(497, 296, 62, 27);
        bt_voice_cancel.setVisible(true);
        this.repaint();

        //	playvoice.run();

    }

    public void acceptYuyin(String Ip)
    {
        /*this.backgroud = "voiceing";
		bt_voice_accept.setVisible(false);
		bt_voice_deny.setVisible(false);
		bt_voice_cancel.setBounds(497, 296, 62, 27);
		bt_voice_cancel.setVisible(true);
		this.repaint();
		*/

        //	show_voice_accept();.

        //	Socket s = playvoice.getSocket();
        //	gainvoice = new Gain_Voice(s); // ��ʼ��һ������
        playvoice = new Play_Voice();
        playvoice.start();

        gainvoice = new Gain_Voice(Ip);
        //	gainvoice.run(); // ��ȡ��Ƶ�豸������
        gainvoice.start();

        this.backgroud = "voiceing";
        bt_voice_accept.setVisible(false);
        bt_voice_deny.setVisible(false);
        bt_voice_cancel.setBounds(497, 296, 62, 27);
        bt_voice_cancel.setVisible(true);
        this.repaint();

    }

    public String getLocalIP()
    {
        InetAddress localAddr = null;
        try
        {
            localAddr = InetAddress.getLocalHost();
        } catch (UnknownHostException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return localAddr.getHostAddress();
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        // TODO Auto-generated method stub
        if (e.getSource() == cb_font)
        {
            send_font = "";
            send_font = cb_font.getSelectedItem().toString();
        }
        else
        {

            send_font_size = cb_size.getSelectedIndex() + 14;
        }
        String str1 = tp_send_message.getText().trim();
        if (str1.length() == 0)
        {
            str1 = " ";
        }
        tp_send_message.setDocs(str1, send_color, send_font, b_bold, b_Italic,
                b_Underline, send_font_size);

    }

    public void sendMessage()
    {

        Date time = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        String timeInfo = format.format(time);

        String str1 = user.getNickName() + "  " + timeInfo + "\n";
        String str = tp_send_message.getText().trim();
        tp_message.setDocs(str1, Color.BLUE, "����", false, false, true, 14);

        if (str.compareTo("") != 0)
        {
            str += "\n";


            tp_message.setDocs(str, send_color, send_font, b_bold, b_Italic,
                    b_Underline, send_font_size);
            info = tp_send_message.getText().trim();

            message.setFontColor(send_color);
            message.setFontSize(send_font_size);
            message.setFontType(send_font);
            message.setBold(b_bold);
            message.setItatic(b_Italic);
            message.setUnderline(b_Underline);
            message.setInfo(info);
            message.setInfoType("TXT");
            // byte buffer[] = ctsT.ObjectToByte(message);
            // ctsT.sendData(buffer);
            ctsT.sendData(message);
            ctsT.sendData(message);


            // ///������Ϣ
            if (sSend.equals("1") == true)
            {
                sSend = "2";
            }
            else
            {
                sSend = "1";
            }

            // /������Ϣ
            IMMessage imMsg = new IMMessage();
            imMsg.setSender(sSend);
            imMsg.setTime(timeInfo);
            imMsg.setMsg(str);
            mModel.addRow(imMsg);

        }

        if (send_Image != null)
        {
            //JOptionPane.showMessageDialog(null, "Length");
            //tp_message.setDocs(str1, Color.BLUE, "����", false, false, true, 14);

            tp_message.insertImage(send_Image);

            image_number = 0;
            tp_message.setDocs("\n", Color.BLUE, "����", false, false, true, 14);

            //TODO:
            String biaoqin = image_ep_number;

            message.setInfoType("Icon");
            message.setBiaoqin(biaoqin);

            ctsT.sendData(message);
            ctsT.sendData(message);
            send_Image = null;
        }
        if (cut_image != null)
        {

            tp_message.insertImage(cut_image);
            tp_message.setDocs("\n", Color.BLUE, "����", false, false, true, 14);
            message.setInfoType("CutImage");
            message.setCutImage(cut_image);

            ctsT.sendData(message);
            ctsT.sendData(message);
            cut_image = null;
        }
			
			/*
			
			*/

        image_number = 0;
        i_flag = false;
        tp_send_message.setText(" ");


        int height = 10;
        Point p = new Point();
        p.setLocation(0, tp_send_message.getHeight() + tp_message.getHeight());
        spanel1.getViewport().setViewPosition(p);

    }

    public ImageIcon sendImage()
    {

        JFileChooser chooser = new JFileChooser();
        ImageIcon image = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ͼƬ�ļ�",
                "jpg", "gif", "png", "jpeg");
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();

            try
            {
                image = new ImageIcon(file.toURI().toURL());

            } catch (MalformedURLException e1)
            {
                e1.printStackTrace();
            }
        }
        return image;

    }

    public void sendFile()
    {

    }

    public void startShake()
    {
        File file = new File("Image/shake.wav");
        AudioClip clip;
        try
        {
            clip = Applet.newAudioClip(file.toURL());
            clip.play();
        } catch (MalformedURLException e2)
        {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        // TODO Auto-generated method stub
        int R = 10;// ��Բ�뾶
        double posx = chatfrm.getLocation().x;
        double posy = chatfrm.getLocation().y;
        int n = 50;
        double alpha = 2 * Math.PI / 10;
        double theta = 0;
        while (n > 0)
        {
            n--;
            theta = theta - alpha;
            double x = posx + Math.cos(theta) * R;
            double y = posy + Math.sin(theta) * R;

            chatfrm.setLocation((int) x, (int) y);
            try
            {
                Thread.sleep(20);
            } catch (InterruptedException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        chatfrm.setLocation((int) posx, (int) posy);
        // shakeTimer.start();//�񶯿�ʼ
    }

    public void getRecord(Hashtable record)
    {
        int size = record.size();
        messageloggingpanel.tp_messagelogging.removeAll();
        Enumeration it = record.elements();
        for (int i = 0; i < size; i++)
        {
            Message message = (Message) it.nextElement();
            String str1 = message.getSendQq() + "  "
                    + message.getDate().toString() + "\n";
            messageloggingpanel.tp_messagelogging.setDocs(str1, Color.BLUE,
                    "����", false, false, true, 14);
            // tp_message.setDocs(str1,Color.BLUE,"����",false,false,true,14);
            String str = message.getInfo() + "\n";
            Color col = message.getFontColor();
            String font = message.getFontType();
            boolean bold = message.getIsBold();
            boolean Italic = message.getIsItatic();
            boolean Underline = message.getIsUnderline();
            int fontSize = message.getFontSize();
            messageloggingpanel.tp_messagelogging.setDocs(str, col, font, bold,
                    Italic, Underline, fontSize);
        }
    }

    public void stopShanke()
    {

        chatfrm.setLocation(start);
        chatfrm.repaint();
    }

    public void setFontPanel()
    {
        // ����
        btn_qp = new MyButton();
        btn_wz = new MyButton();
        btn_qp.addActionListener(this);
        btn_wz.addActionListener(this);
        btn_qp.setBounds(2, 344, 58, 25);
        btn_wz.setBounds(58, 344, 60, 25);
        btn_qp.setVisible(false);
        btn_wz.setVisible(false);
        add(btn_qp);
        add(btn_wz);
        //

        font_panel = new JPanel();
        cb_font = new JComboBox();
        cb_size = new JComboBox();
        bt_bold = new MyButton(); // ����
        bt_Italic = new MyButton();// б��
        bt_Underline = new MyButton();// �»���
        bt_color = new MyButton(); // ��ɫ

        bt_bold.setToolTipText("cuti");
        bt_Italic.setToolTipText("xieti");
        bt_Underline.setToolTipText("xiahuaxian");
        bt_color.setToolTipText("yanse");

        cb_font.addItemListener(this);
        cb_size.addItemListener(this);
        bt_bold.addActionListener(this);
        bt_Italic.addActionListener(this);
        bt_Underline.addActionListener(this);
        bt_color.addActionListener(this);

        cb_font.addItem("����");
        cb_font.addItem("����");
        cb_font.addItem("����");
        cb_font.addItem("��������");
        cb_font.addItemListener(this);
        for (int i = 14; i < 36; i++)
        {
            String s = "" + i;
            cb_size.addItem(s);
        }
        cb_size.setSelectedItem(send_font_size);
        font_panel.setBounds(235, 345, 208, 25);
        font_panel.setLayout(null);

        cb_font.setOpaque(false);
        cb_size.setOpaque(false);
        bt_color.setContentAreaFilled(false);

        cb_font.setBounds(0, 0, 75, 25);
        cb_size.setBounds(78, 0, 40, 25);
        bt_bold.setBounds(118, 0, 22, 25);
        icon = image.LoadImageIcon("bold");
        bt_bold.setIcon(icon);
        bt_Italic.setBounds(140, 0, 22, 25);
        bt_Underline.setBounds(162, 0, 22, 25);
        bt_color.setBounds(184, 0, 23, 25);

        font_panel.add(cb_font);
        font_panel.add(cb_size);
        font_panel.add(bt_bold);
        font_panel.add(bt_Italic);
        font_panel.add(bt_Underline);
        font_panel.add(bt_color);

        font_panel.setOpaque(false);
        font_panel.setVisible(false);
        add(font_panel);

    }

    public void setMessage()
    {// ������ʾ��ǰ�Ự���
        tp_message.setOpaque(false); // ͸��
        tp_message.setEditable(false); // ���ɱ༭
        // tp_message.setLineWrap(true); //�Զ�����

        tp_message.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent mouseEvent)
            {
                tp_message.setCursor(new Cursor(Cursor.TEXT_CURSOR)); // ������Text�����Ϊ�ı�����ָ��
            }

            public void mouseExited(MouseEvent mouseEvent)
            {
                tp_message.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ����뿪Text����ָ�Ĭ����̬
            }
        });

        spanel1 = new JScrollPane(tp_message);
        spanel1.setOpaque(false); // ͸��
        spanel1.getViewport().setOpaque(false);// ͸��
        spanel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        spanel1.setVisible(true);
        spanel1.setBounds(0, 83, 447, 290);

        Bt_Qipao();
        spanel1.setViewportView(tp_message);
        add(spanel1);
        jsbar = spanel1.getHorizontalScrollBar();
        if (jsbar == null)
        {
            jsbar.setValue(jsbar.getHeight());
        }
    }

    public void setSendMessage()
    {// ���÷�����Ϣpanel
        tp_send_message.setDocs("", send_color, send_font, b_bold, b_Italic,
                b_Underline, send_font_size);
        tp_send_message.setOpaque(false); // ͸��
        tp_send_message.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {
                // TODO Auto-generated method stub
                int keyChar = e.getKeyChar();
                if (keyChar == KeyEvent.VK_ENTER)
                {// �س�������Ϣ
                    {
                        sendMessage();

                    }

                }
                else
                {

                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                // TODO Auto-generated method stub

            }

        });

        tp_send_message.setForeground(new Color(1, 0, 1));
        tp_send_message.setCaretColor(new Color(0, 0, 1));
        spanel2 = new JScrollPane(tp_send_message);
        spanel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        spanel2.setOpaque(false); // ͸��
        spanel2.getViewport().setOpaque(false);// ͸��
        spanel2.setVisible(true);
        spanel2.setBounds(0, 402, 447, 70);
        add(spanel2);

    }

    public void insertSendPic(ImageIcon imgIc)
    {// ���뷢��ͼƬ
        send_Image = imgIc;
        tp_send_message.insertImage(imgIc); // ����ͼƬ
        i_flag = false;
        System.out.print(imgIc.toString());
        // insert(new FontAttrib()); // ���������Ի���
    }

    public JButton getPicBtn()
    {
        return bt_erpression;
    }

    public void Bt_Qipao()
    {// ����ģʽ��ť��Ӧ
        // ������Ϣ
        table.setTableHeader(null);
        table.setModel(mModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(0)
                .setCellRenderer(new BubbleRenderer());
        spanel1.setViewportView(table);
        table.setOpaque(false);
        table.setShowHorizontalLines(false);
        // ������Ϣ
    }

    public void insertCutImage(ImageIcon im)
    {// �����ͼ
        cut_image = new ImageIcon();
        cut_image = im;
        tp_send_message.insertImage(cut_image);
    }

    public void insertUpCutImage(ImageIcon im, String friend)
    {
        Date time = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        String timeInfo = format.format(time);
        String str1 = friend + "  " + timeInfo + "\n";
        tp_message.setDocs(str1, Color.BLUE, "����", false, false, true, 14);
        tp_message.insertImage(im);
        tp_message.setDocs("\n", Color.BLUE, "����", false, false, true, 14);

    }

    public void insertImage(String biaoqin)
    {
        //TODO:�ָ�����ַ�������ʾ���Ự���
        LoadImages li = new LoadImages();
        ImageIcon ic = new ImageIcon(li.loadEpImage(biaoqin));
        tp_message.insertImage(ic);
    }

    public void insertImage1(String biaoqin)
    {
        Date time = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        String timeInfo = format.format(time);
        String str1 = friend.getNickName() + "  " + timeInfo + "\n";
        tp_message.setDocs(str1, Color.BLUE, "����", false, false, true, 14);
        LoadImages li = new LoadImages();
        ImageIcon ic = new ImageIcon(li.loadEpImage(biaoqin));
        tp_message.insertImage(ic);
    }

    public ChatPanel getChatPanel()
    {// �õ���ǰ���ָ��
        return this;
    }

    public void show_voice_accept()
    {// ��ʾ�����������
        this.backgroud = "bk5";
        chatfrm.setSize(599, 508);
        bt_voice.setEnabled(false);
        bt_video.setEnabled(false);
        chatfrm.btn_close.setBounds(573, 1, 25, 26);
        bt_voice_cancel.setVisible(false);
        bt_voice_accept.setVisible(true);
        bt_voice_deny.setVisible(true);
        bt_message_logging.setEnabled(false);
        this.repaint();
    }

    public void bt_Voice()
    {// ��������
        this.backgroud = "bk4";
        chatfrm.setSize(599, 508);
        bt_voice.setEnabled(false);
        bt_video.setEnabled(false);
        chatfrm.btn_close.setBounds(573, 1, 25, 26);
        bt_voice_cancel.setVisible(true);
        bt_message_logging.setEnabled(false);
        this.repaint();
    }

    public void bt_File_Send()
    {// �����ļ�
        lb_file_name.setVisible(true);
        this.backgroud = "file_send";
        chatfrm.setSize(730, 508);
        chatfrm.btn_close.setBounds(702, 1, 25, 26);
        bt_message_logging.setEnabled(false);
        bt_file_cancel.setVisible(true);
        lb_file_name.setText("wenjianming");
        lb_file_name.setVisible(true);
        this.repaint();
    }

    public void bt_File_Accept()
    {// �����ļ�
        lb_file_name.setVisible(true);
        this.backgroud = "file_accept";
        chatfrm.setSize(730, 508);
        chatfrm.btn_close.setBounds(702, 1, 25, 26);
        bt_message_logging.setEnabled(false);
        bt_file_accept.setVisible(true);
        bt_file_save.setVisible(true);
        bt_file_deny.setVisible(true);
        lb_file_name.setText("wenjianming");
        lb_file_name.setVisible(true);
        this.repaint();
    }

    public void closeCtst()
    {
        this.ctsT.closeSocket();
    }

    /**
     * �÷���������÷����������ļ��е�IP��PORT
     */
    private void getPropertiesInfo()
    {
        Properties prop = new Properties();
        InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ServerInfo.properties");
        try
        {
            //�����Ӧ�ļ�ֵ��
            prop.load(inStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //������Ӧ�ļ���ö�Ӧ��ֵ

        try
        {
            receivePort = ReceivePort.getPort();
            System.out.println("pp" + receivePort);
            //	JOptionPane.showMessageDialog(null, "person:"+receivePort);
            Port.hash.put(friend.getQq(), receivePort);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //sendPORT = Integer.parseInt(prop.getProperty("sendudp.port"));

    }

    public static DatagramSocket getRandomPort() throws SocketException
    {
        DatagramSocket s = new DatagramSocket(0);
        return s;
    }

    public DatagramSocket getRangePort(int[] ports) throws IOException
    {
        for (int port : ports)
        {
            try
            {
                return new DatagramSocket(port);
            } catch (IOException ex)
            {
                continue; // try next port
            }
        }

        // if the program gets here, no port in the range was found
        throw new IOException("no free port found");
    }

    public void set_ep_number(int number)
    {
        image_ep_number = "" + number;
        System.out.println("���飺" + image_ep_number);
    }

    public void voice_cancel()
    {

        //	gainvoice.stop();
        //	playvoice.stop();
        this.backgroud = "bk";
        chatfrm.setSize(586, 508);
        bt_voice.setEnabled(true);
        bt_video.setEnabled(true);
        chatfrm.btn_close.setBounds(558, 1, 28, 26);
        bt_voice_cancel.setVisible(false);
        bt_message_logging.setEnabled(true);
        this.repaint();
    }
}
