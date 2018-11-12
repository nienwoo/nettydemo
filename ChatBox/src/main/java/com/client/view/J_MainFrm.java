/**
 * @Title:J_MainFrm.java
 * @Package:com.client.view
 * @Description:TODO(�������ļ���ʲô)
 * @author: ShiLuoDeQin
 * @date:2013-12-25����01:43:10
 * @version V1.0
 */

package com.client.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.client.business.mainRender.ColorConvertOp;
import com.client.business.mainRender.CombListRenderer;

import com.client.business.mainRender.IconNodeRenderer;
import com.client.business.mainRender.InformationFrm;
import com.client.business.mainRender.MyTreeUI1;
import com.client.business.mainRender.findFriendFrm;
import com.client.business.uiManager.Login;
import com.client.data.ClientToServer;
import com.common.Port;
import com.common.UserInfoBean;
import com.tools.ClientToServerThread;
import com.client.business.mainRender.IconNodeRenderer;
import com.client.business.mainRender.IconNode;

/**
 * @ClassName:J_MainFrm
 * @author Administrator
 * @Description:TODO(��������������)
 * @date 2013-12-25 ����01:43:10
 *
 */

public class J_MainFrm extends JFrame
{
    private static final long serialVersionUID = 1L;
    J_MainFrm MainFrm;
    public IconNode Root = new IconNode(null, null);//������ڵ�
    JPanel abc = new JPanel();//��ӿؼ������
    int Mwide = 300, Mheight = 650;//����ĳ���
    private int x, y;//�����λ�ã��϶�ʱ��λ��
    int MAX_wide = 500;//�����
    private int offsetX, offsetY;//�϶�����ƫ��λ��
    Image ImageBk;    //����ͼƬ
    JLabel Bk_label;//����label
    boolean isHide = false;//�����Ƿ�����
    JLabel nickname = new JLabel();
    JLabel sign = new JLabel();
    public JTree tree;                       //�����б�
    public findFriendFrm FindFriendFrm = null;//���Һ������
    public int groupNumber = 0;//��ŷ������
    public String groupName[] = new String[20];//��ŷ�������

    private JTabbedPane tabeld = null;
    /**
     * ͼƬ��Դ
     */
    static String Background = "Image/MainIcon/qq.png";
    static String Pclose = "Image/MainIcon/close.png";//
    static String Pclose1 = "Image/MainIcon/close1.png";
    static String Pmin = "Image/MainIcon/min.png";
    static String Pmin1 = "Image/MainIcon/min1.png";
    static String Pskin = "Image/MainIcon/skin.png";
    static String Pskin1 = "Image/MainIcon/skin1.png";
    static String touxiang = "Image/MainIcon/qqicons\\DefaultFace.png";
    static String select = "Image/MainIcon/qqicons\\select.png";
    static String Ptask = "Image/MainIcon/task.png";//

    static String touxiang1 = "Image/MainIcon/qqicons\\Catch00001.jpg";
    static String touxiang2 = "Image/MainIcon/qqicons\\Catch00002.jpg";
    static String touxiang3 = "Image/MainIcon/qqicons\\Catch00003.jpg";
    static String touxiang4 = "Image/MainIcon/qqicons\\Catch00004.jpg";
    static String touxiang5 = "Image/MainIcon/qqicons\\Catch00005.jpg";
    static String touxiang6 = "Image/MainIcon/qqicons\\Catch00006.jpg";
    static String treeNode = "Image/MainIcon/treeNode.png";
    static String treeNode1 = "Image/MainIcon/treeNode1.png";
    static String Pmask = "Image/MainIcon/mask.png";
    //////////// ״̬ͼ��
    static String Ponline = "Image/MainIcon/Status\\imonline.png";
    static String Poffline = "Image/MainIcon/Status\\imoffline.png";
    static String Pbusy = "Image/MainIcon/Status\\busy.png";
    static String away = "Image/MainIcon/Status\\away.png";
    static String invisible = "Image/MainIcon/Status\\invisible.png";
    static String Qme = "Image/MainIcon/Status\\Qme.png";
    static String mute = "Image/MainIcon/Status\\mute.png";
    ///C:\Users\Administrator\Desktop\�γ����\topIcon
//	/////////////////////////��������ͼ�갴ť
    static String Pzone = "Image/MainIcon/topIcon\\qzone.png";
    static String Pblog = "Image/MainIcon/topIcon\\wblog.png";
    static String Pemail = "Image/MainIcon/topIcon\\mail.png";
    static String Pshop = "Image/MainIcon/topIcon\\paipai.png";
    static String Pmoney = "Image/MainIcon/topIcon\\purse.png";
    static String Pmeber = "Image/MainIcon/topIcon\\friend.png";
    static String Pweb = "Image/MainIcon/topIcon\\soso.png";
    //	//	/////////////////////////��ײ������ͼ�갴ť
    static String PApp = "Image/MainIcon/MainTopToolBar\\appbox_mgr_btn.png";
    static String QQSafe = "Image/MainIcon/MainTopToolBar\\QQSafe.png";
    static String SystemSet = "Image/MainIcon/MainTopToolBar\\SystemSet.png";
    static String find = "Image/MainIcon/MainTopToolBar\\find.png";
    static String message = "Image/MainIcon/MainTopToolBar\\message.png";
    static String groupmainpage = "Image/MainIcon/MainTopToolBar\\groupmainpage.png";
    String face = "";
    /////////////////////////////////
    public ClientToServer cts;
    Login logout;
    private UserInfoBean userInfo;
    public Hashtable friendInfoTable;
    public int n;

    //////////////////////////////
    /*
	 * ���췽��
	 */
    public J_MainFrm(ClientToServer cts, UserInfoBean userInfo, Hashtable friendInfoTable, int n) throws IOException
    {
        this.cts = cts;
        logout = new Login(cts);
        this.userInfo = userInfo;
        this.friendInfoTable = friendInfoTable;
        this.n = n;

        System.out.println(Background);
        this.setTitle("QQ3014");
        nickname.setBounds(0, 0, 100, 30);
        nickname.setLocation(3, 10);
        nickname.setFont(new java.awt.Font("����", Font.BOLD, 30));
        nickname.setForeground(Color.MAGENTA);
        nickname.setText(userInfo.getNickName());

        sign.setBounds(100, 0, 300, 30);
        sign.setLocation(70, 10);
        sign.setFont(new java.awt.Font("����", Font.ITALIC, 18));
        sign.setForeground(Color.GREEN);
        sign.setText("(" + userInfo.getSign() + ")");
        ShowTrayIcon();
        face = "Image/MainIcon/qqicons\\Catch0000" + userInfo.getPhotoID() + ".jpg";
        ShowTouXiang(this);

        abc.setBounds(this.getBounds());
        abc.setLayout(null);
        abc.setOpaque(false);
        abc.add(nickname); //�ǳ�
        abc.add(sign);

        MainFrm = this;
        this.setResizable(false);
        this.setUndecorated(true);//����Ҫ��������װ��
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (screenSize.getWidth() - Mwide) / 2,
                (int) (screenSize.getHeight() - Mheight) / 2);
        this.setSize(Mwide, Mheight);
        this.setBackground(Color.blue);
        this.addMouseMotionListener(new MyMouseAdapter());
        this.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
                if (isHide)
                {
                    MainFrm.setLocation(MainFrm.getLocationOnScreen().x, 0);
                    isHide = false;
                    MainFrm.setAlwaysOnTop(false);
                }
            }

            public void mouseExited(MouseEvent e)
            {
                if (MainFrm.getLocationOnScreen().y <= 0)
                {

                    MainFrm.setLocation(MainFrm.getLocationOnScreen().x, 3 - Mheight);
                    isHide = true;
                    MainFrm.setAlwaysOnTop(true);
                }
            }

            public void mousePressed(MouseEvent e)
            {
                offsetX = e.getX();
                offsetY = e.getY();
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        //////����ͼƬ
        Bk_label = new JLabel();
        Bk_label.setBounds(MainFrm.getBounds());
        Bk_label.setLocation(0, 0);
        Bk_label.setIcon(new ImageIcon(Background));

        ////////////״̬��ť/�������ߵȡ�����

        JComboBox icb = new JComboBox();//�����׼��Ͽ�
        icb.setMaximumRowCount(7);//���������ʾ��
        icb.setRenderer(new CombListRenderer());//���õ�Ԫ�����ã�����ʹ�������ǸղŴ������ࣩ
        icb.setBackground(new Color(112, 100, 200, 166));//���ñ���ɫ

        icb.addItem(new Object[]{new ImageIcon(Ponline), "����"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(Poffline), "����"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(Pbusy), "æµ"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(away), "�뿪"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(invisible), "����"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(Qme), "Q��"});//���ѡ��
        icb.addItem(new Object[]{new ImageIcon(mute), "����"});//���ѡ��


        icb.setBounds(0, 0, 80, 20);
        icb.setLocation(75, 45);
        icb.setBorder(null);

        abc.add(icb);


//		****************������***************************
        JTextArea serch = new JTextArea("����...");
        serch.setToolTipText("����QQ���롢����/�ǳơ�ƴ����Email����\n\r\t��ϵ�ˣ�" +
                "������ͨ��������QQ�������İ��\n��");
        serch.setBackground(Color.gray);
        serch.setBounds(0, 0, 296, 30);
        serch.setLocation(2, 100);
        abc.add(serch);
//		*****************�����������ͼ�갴ť***************8 
        JButton BZone, BBlog, BEmail, BShop, BMoney, BMember, BWeb;
        BZone = new JButton();        //�ռ�
        BZone.setBounds(0, 0, 20, 20);
        BZone.setLocation(80, 75);
        BZone.setIcon(new ImageIcon(Pzone));
        BZone.setContentAreaFilled(false);
        BZone.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub

                Update();

                JOptionPane.showMessageDialog(null, "��ˢ�º����б��ˣ�tree");
            }
        });
        abc.add(BZone);

        BBlog = new JButton();        //΢��
        BBlog.setBounds(0, 0, 20, 20);
        BBlog.setLocation(100, 75);
        BBlog.setIcon(new ImageIcon(Pblog));
        BBlog.setContentAreaFilled(false);
        abc.add(BBlog);

        BEmail = new JButton();        //Email
        BEmail.setBounds(0, 0, 20, 20);
        BEmail.setLocation(120, 75);
        BEmail.setIcon(new ImageIcon(Pemail));
        BEmail.setContentAreaFilled(false);
        abc.add(BEmail);

        BShop = new JButton();        //����
        BShop.setBounds(0, 0, 20, 20);
        BShop.setLocation(140, 75);
        BShop.setIcon(new ImageIcon(Pshop));
        BShop.setContentAreaFilled(false);
        abc.add(BShop);

        BMoney = new JButton();        //Ǯ��
        BMoney.setBounds(0, 0, 20, 20);
        BMoney.setLocation(160, 75);
        BMoney.setIcon(new ImageIcon(Pmoney));
        BMoney.setContentAreaFilled(false);
        abc.add(BMoney);

        BMember = new JButton();        //��Ա
        BMember.setBounds(0, 0, 20, 20);
        BMember.setLocation(180, 75);
        BMember.setIcon(new ImageIcon(Pmeber));
        BMember.setContentAreaFilled(false);
        abc.add(BMember);

        BWeb = new JButton();            //������վ
        BWeb.setBounds(0, 0, 20, 20);
        BWeb.setLocation(200, 75);
        BWeb.setIcon(new ImageIcon(Pweb));
        BWeb.setContentAreaFilled(false);
        abc.add(BWeb);

//		 ******************���������ͼ�갴ť����**************************

//		******************�����������ͼ�갴ťbegin**************************
        JButton Bcai, Bgame, Bpet, Bmusic, Bvedio, Btuan, Bmanager,
                Bxun, Bapp, Bmain, Bset, Bmessage, Bfile, Bserch, BAPP;
        Bcai = new JButton();        //QQ��Ʊ
        Bcai.setBounds(0, 0, 20, 20);
        Bcai.setLocation(5, 590);
        Bcai.setIcon(new ImageIcon(Pzone));
        Bcai.setContentAreaFilled(false);
        abc.add(Bcai);

        Bgame = new JButton();        //QQ��Ϸ
        Bgame.setBounds(0, 0, 20, 20);
        Bgame.setLocation(35, 590);
        Bgame.setIcon(new ImageIcon(Pblog));
        Bgame.setContentAreaFilled(false);
        abc.add(Bgame);

        Bpet = new JButton();        //QQ����
        Bpet.setBounds(0, 0, 20, 20);
        Bpet.setLocation(65, 590);
        Bpet.setIcon(new ImageIcon(Pemail));
        Bpet.setContentAreaFilled(false);
        abc.add(Bpet);

        Bmusic = new JButton();        //QQ����
        Bmusic.setBounds(0, 0, 20, 20);
        Bmusic.setLocation(95, 590);
        Bmusic.setIcon(new ImageIcon(Pshop));
        Bmusic.setContentAreaFilled(false);
        abc.add(Bmusic);

        Bvedio = new JButton();        //��Ѷ��Ƶ
        Bvedio.setBounds(0, 0, 20, 20);
        Bvedio.setLocation(125, 590);
        Bvedio.setIcon(new ImageIcon(Pmoney));
        Bvedio.setContentAreaFilled(false);
        abc.add(Bvedio);

        Btuan = new JButton();        //QQ�Ź�
        Btuan.setBounds(0, 0, 20, 20);
        Btuan.setLocation(155, 590);
        Btuan.setIcon(new ImageIcon(Pmeber));
        Btuan.setContentAreaFilled(false);
        abc.add(Btuan);

        Bmanager = new JButton();            //���Թܼ�
        Bmanager.setBounds(0, 0, 20, 20);
        Bmanager.setLocation(185, 590);
        Bmanager.setIcon(new ImageIcon(QQSafe));
        Bmanager.setContentAreaFilled(false);
        abc.add(Bmanager);

        Bxun = new JButton();            //��Ѹ��
        Bxun.setBounds(0, 0, 20, 20);
        Bxun.setLocation(215, 590);
        Bxun.setIcon(new ImageIcon(Pweb));
        Bxun.setContentAreaFilled(false);
        abc.add(Bxun);

        Bapp = new JButton();            //Ӧ�ù�����
        Bapp.setBounds(0, 0, 20, 20);
        Bapp.setLocation(270, 590);
        Bapp.setIcon(new ImageIcon(PApp));
        Bapp.setContentAreaFilled(false);
        abc.add(Bapp);


        Bmain = new JButton();            //���˵�
        Bmain.setBounds(0, 0, 20, 20);
        Bmain.setLocation(5, 620);
        Bmain.setIcon(new ImageIcon(Pweb));
        Bmain.setContentAreaFilled(false);
        abc.add(Bmain);

        Bset = new JButton();            //ϵͳ����
        Bset.setBounds(0, 0, 20, 20);
        Bset.setLocation(35, 620);
        Bset.setIcon(new ImageIcon(SystemSet));
        Bset.setContentAreaFilled(false);
        abc.add(Bset);

        Bmessage = new JButton();            //��Ϣ����
        Bmessage.setBounds(0, 0, 20, 20);
        Bmessage.setLocation(65, 620);
        Bmessage.setIcon(new ImageIcon(message));
        Bmessage.setContentAreaFilled(false);
        abc.add(Bmessage);

        Bfile = new JButton();            //�ļ�����
        Bfile.setBounds(0, 0, 20, 20);
        Bfile.setLocation(95, 620);
        Bfile.setIcon(new ImageIcon(Pweb));
        Bfile.setContentAreaFilled(false);
        abc.add(Bfile);

        Bserch = new JButton();            //����
        Bserch.setBounds(0, 0, 20, 20);
        Bserch.setLocation(125, 620);
        Bserch.setIcon(new ImageIcon(find));
        Bserch.setContentAreaFilled(false);
        Bserch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (FindFriendFrm == null)
                {
                    FindFriendFrm = new findFriendFrm(MainFrm, MainFrm.userInfo);
                }
            }
        });

        abc.add(Bserch);

        BAPP = new JButton();            //Ӧ�ñ�
        BAPP.setBounds(0, 0, 20, 20);
        BAPP.setLocation(240, 620);
        BAPP.setIcon(new ImageIcon(groupmainpage));
        BAPP.setContentAreaFilled(false);
        abc.add(BAPP);

//		******************���������ͼ�갴ťend**************************
        final JButton B_close = new JButton("�ر�");
        B_close.setBounds(0, 0, 30, 20);
        B_close.setLocation(this.getBounds().width - 20, 0);
        B_close.setIcon(new ImageIcon(Pclose1));
        B_close.setOpaque(false);
        B_close.setBorder(null);
        B_close.setBackground(Color.white);
        B_close.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (JOptionPane.showConfirmDialog(null, "<html><font size=3>ȷ���˳���</html>", "ϵͳ��ʾ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0)
                {
                    try
                    {
                        logout.userLogout();
                    } catch (Exception e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    ClientToServerThread ctst = Port.comm.get(1);
                    ctst.closeSocket();
                    System.exit(0);
                }
                else
                {
                    return;
                }
            }

            public void mouseEntered(MouseEvent e)
            {
                B_close.setIcon(new ImageIcon(Pclose));
            }

            public void mouseExited(MouseEvent e)
            {
                B_close.setIcon(new ImageIcon(Pclose1));
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        abc.add(B_close);

        final JButton B_min = new JButton("min");
        B_min.setBounds(0, 0, 30, 20);
        B_min.setLocation(this.getBounds().width - 40, 0);
        B_min.setIcon(new ImageIcon(Pmin));
        B_min.setOpaque(false);
        B_min.setBorder(null);
        B_min.setBackground(Color.white);
        B_min.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                dispose();//������С��ʱdispose�ô���

            }

            public void mouseEntered(MouseEvent e)
            {
                B_min.setIcon(new ImageIcon(Pmin1));
            }

            public void mouseExited(MouseEvent e)
            {
                B_min.setIcon(new ImageIcon(Pmin));
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        abc.add(B_min);

        final JButton B_skin = new JButton("min");
        B_skin.setBounds(0, 0, 30, 20);
        B_skin.setLocation(this.getBounds().width - 60, 0);
        B_skin.setIcon(new ImageIcon(Pskin));
        B_skin.setOpaque(false);
        B_skin.setBorder(null);
        B_skin.setBackground(Color.white);
        B_skin.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {

            }

            public void mouseEntered(MouseEvent e)
            {
                B_skin.setIcon(new ImageIcon(Pskin1));
            }

            public void mouseExited(MouseEvent e)
            {
                B_skin.setIcon(new ImageIcon(Pskin));
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        abc.add(B_skin);
        //////////////
        ///ѡ�
        UpdateFriendList();

        ////
        JLabel bk = new JLabel();
        bk.setBounds(0, 0, 300, 420);
        bk.setLocation(0, 157);
        bk.setIcon(new ImageIcon(Pmask));
        abc.add(bk);

        abc.add(Bk_label);

        this.add(abc);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);

        /**����رհ�ť�¼�*/

    }

    class MyMouseAdapter extends MouseAdapter
    {


        public void mouseDragged(MouseEvent e)
        {
            x = e.getXOnScreen();
            y = e.getYOnScreen();

            if (y <= 0)
            {
                y = 0;

            }
            setLocation(x - offsetX, y - offsetY);
        }
    }

    public void ShowTrayIcon()
    {
        /////����
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(Ptask);
        SystemTray systemTray = SystemTray.getSystemTray();//���ϵͳ���̵�ʵ��
        /////��ӵ����˵�
        PopupMenu popupMenu = new PopupMenu(); // ���������˵�
        MenuItem exit = new MenuItem("�˳�"); // �����˵���
        //��Ӧ����
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);
            }
        });
        popupMenu.add(exit); // Ϊ�����˵���Ӳ˵���
        /////////
        final TrayIcon trayIcon = new TrayIcon(img, Ptask);
        try
        {

            systemTray.add(trayIcon);//�������̵�ͼ�꣬0.gif������ļ�ͬһĿ¼
        } catch (AWTException e2)
        {
            e2.printStackTrace();
        }

        trayIcon.setPopupMenu(popupMenu); // Ϊ����ͼ��ӵ����˵�
        trayIcon.setToolTip("QQ:1064308261\n����������\n��Ϣ���ѿ򣺹ر�\n�Ự��Ϣ��������ͷ������");

        trayIcon.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)//˫�����̴�������
                {
                    setExtendedState(JFrame.NORMAL);//״̬
                    setVisible(true);
                }
            }

            public void mouseEntered(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        /////
    }

    public void ShowTouXiang(J_MainFrm MainFrm)
    {
        /**
         * �÷�����ʾͷ���
         */
        final JLabel Ltouxiang = new JLabel();
        final JLabel Lselect = new JLabel();
        Ltouxiang.setBounds(0, 0, 68, 68);
        Ltouxiang.setLocation(10, 30);
        ImageIcon icon = new ImageIcon();
        Ltouxiang.setIcon(icon);

        Lselect.setBounds(0, 0, 60, 60);
        Lselect.setLocation(14, 34);
        ImageIcon icon1 = new ImageIcon(MainFrm.face);
        Lselect.setIcon(icon1);
        MainFrm.add(Lselect);

        Ltouxiang.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                new InformationFrm(userInfo);
            }

            public void mouseEntered(MouseEvent e)
            {
                Ltouxiang.setIcon(new ImageIcon(select));
            }

            public void mouseExited(MouseEvent e)
            {
                Ltouxiang.setIcon(new ImageIcon());
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        MainFrm.add(Ltouxiang);

    }

    public JTabbedPane createFriendList(ClientToServer cts, final UserInfoBean userInfo, final Hashtable friendInfoTable, final int n) throws IOException
    {
//		*********************����Ĭ�Ϸ���**
        groupNumber = 0;
        IconNode haoyou = new IconNode("����");
        IconNode strager = new IconNode("İ����");
        IconNode black = new IconNode("������");
        //haoyou.add(new IconNode(""));
        //strager.add(new IconNode(""));
        //black.add(new IconNode(""));
        Root = new IconNode(null, null);
        Root.add(haoyou);


//		***********************���ݴ����ݿ��ȡ�ķ���ͺ��ѵ���Ϣ���������б�

        IconNode root[] = new IconNode[20]; //�������20������


        boolean isfind = false;//�������ҵ���ͬ����

        String groupname = "";//�����ʱȡ�����ķ�������
        int total = 0;//����ܵķ�����

        Enumeration it = friendInfoTable.elements();

        for (int i = 0; i < n; i++)
        {
            total = groupNumber;
            isfind = false;
            UserInfoBean friend = (UserInfoBean) it.nextElement();
            System.out.println("�½������У�" + friend.getQq() + " " + friend.getNickName() + " " +
                    friend.getSubGroupName() + "���߷�" + friend.getStatus());
            ///���к����еķ������ֱȽ�
            groupname = friend.getSubGroupName();
            if (groupNumber == 0)
            {
                groupNumber++;
                groupName[groupNumber] = groupname;
                root[groupNumber] = new IconNode(groupName[groupNumber]);//��������
                face = "Image/MainIcon/qqicons\\Catch0000" + friend.getPhotoID() + ".jpg";
                if (!friend.getStatus())
                {//��������ߣ������ͷ��
                    try
                    {
                        IconNode friendNode = new IconNode(ColorConvertOp.getGrayPicture(face), friend.getNickName() +//��Ӻ��ѵ������б���
                                "  " + "(" + friend.getQq() + ")", friend.getSign());
                        root[groupNumber].add(friendNode);
                        friendNode.setQQ(Integer.toString(friend.getQq()));
                    } catch (IOException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else
                {
                    IconNode friendNode = new IconNode(new ImageIcon(face), friend.getNickName() +//��Ӻ��ѵ������б���
                            "  " + "(" + friend.getQq() + ")", friend.getSign());
                    root[groupNumber].add(friendNode);
                    friendNode.setQQ(Integer.toString(friend.getQq()));
                }
                Root.add(root[groupNumber]);

            }
            else
            {///���к����еķ������ֱȽ�
                for (int j = 1; j <= total; j++)
                {
                    if (groupName[j].equals(groupname))
                    {//������
                        isfind = true;//�ҵ���
                        face = "Image/MainIcon/qqicons\\Catch0000" + friend.getPhotoID() + ".jpg";
                        if (!friend.getStatus())
                        {//��������ߣ������ͷ��
                            try
                            {
                                IconNode friendNode = new IconNode(ColorConvertOp.getGrayPicture(face), friend.getNickName() +//��Ӻ��ѵ������б���
                                        "  " + "(" + friend.getQq() + ")", friend.getSign());
                                root[groupNumber].add(friendNode);
                                friendNode.setQQ(Integer.toString(friend.getQq()));
                            } catch (IOException e1)
                            {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        else
                        {
                            IconNode friendNode = new IconNode(new ImageIcon(face), friend.getNickName() +//��Ӻ��ѵ������б���
                                    "  " + "(" + friend.getQq() + ")", friend.getSign());
                            root[groupNumber].add(friendNode);
                            friendNode.setQQ(Integer.toString(friend.getQq()));
                        }
                        Root.add(root[groupNumber]);
                        break;
                    }
                    else if (j >= groupNumber && !isfind)
                    {//��������
                        groupNumber++;
                        groupName[groupNumber] = groupname;
                        root[groupNumber] = new IconNode(groupName[groupNumber]);//��������
                        face = "Image/MainIcon/qqicons\\Catch0000" + friend.getPhotoID() + ".jpg";
                        if (!friend.getStatus())
                        {//��������ߣ������ͷ��
                            try
                            {
                                IconNode friendNode = new IconNode(ColorConvertOp.getGrayPicture(face), friend.getNickName() +//��Ӻ��ѵ������б���
                                        "  " + "(" + friend.getQq() + ")", friend.getSign());
                                root[groupNumber].add(friendNode);
                                friendNode.setQQ(Integer.toString(friend.getQq()));
                            } catch (IOException e1)
                            {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        else
                        {
                            IconNode friendNode = new IconNode(new ImageIcon(face), friend.getNickName() +//��Ӻ��ѵ������б���
                                    "  " + "(" + friend.getQq() + ")", friend.getSign());
                            root[groupNumber].add(friendNode);
                            friendNode.setQQ(Integer.toString(friend.getQq()));
                        }
                        Root.add(root[groupNumber]);
                    }
                }
            }


        }
        Root.add(strager);
        Root.add(black);//��Ӻ�����
        UIManager.put("Tree.collapsedIcon", new ImageIcon(treeNode));
        UIManager.put("Tree.expandedIcon", new ImageIcon(treeNode1));
        UIManager.put("Tree.OffX", new ImageIcon(treeNode1));

        final JTree tree = new JTree(Root);
        final IconNodeRenderer render = new IconNodeRenderer();


        render.setBackground(Color.pink);
        tree.setCellRenderer(render); //���õ�Ԫ������
        tree.setEditable(false); //�������Ƿ�ɱ༭
        tree.setRootVisible(false);//�������ĸ��ڵ��Ƿ����
        tree.setRowHeight(50);       //�����о�

        tree.setShowsRootHandles(true); //��ʾ�۵�/չ�� ͼ��

        tree.setToggleClickCount(1);//���õ�������չ�����ڵ�

        tree.setOpaque(false);
        tree.setUI(new MyTreeUI1()
        {
        });

        tree.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)//˫���ڵ�
                {

                    TreePath path = tree.getSelectionPath();//��ȡѡ�нڵ�·��
                    Object node = path.getLastPathComponent();//ͨ��·����ָ��ָ��ýڵ�
                    if (((IconNode) node).isLeaf())//����ýڵ���Ҷ�ӽڵ�

                    {
                        System.out.println("Ҷ�ӽڵ������Ϊ��" + ((IconNode) node).getText() + "QQΪ��" + ((IconNode) node).getQQ());
                        String nodeQQ = ((IconNode) node).getQQ();

                        Enumeration it = friendInfoTable.elements();

                        for (int i = 0; i < n; i++)
                        {

                            UserInfoBean friend = (UserInfoBean) it.nextElement();
                            String qq = Integer.toString(friend.getQq());
                            if (nodeQQ.equals(qq))
                            {
                                //TODO:
                                String sendIP = "127.0.0.1";
                                String receiveIP = "127.0.0.1";

                                InetAddress localAddr = null;
                                try
                                {
                                    localAddr = InetAddress.getLocalHost();
                                } catch (UnknownHostException e1)
                                {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }

                                receiveIP = localAddr.getHostAddress();

                                MainFrm.userInfo.setIP(receiveIP);
                                friend.setIP(receiveIP);
                                JChatFrm jcf = new JChatFrm(MainFrm.userInfo, friend);
                                jcf.setVisible(true);
                                break;
                            }
                        }

//			    	   JOptionPane.showMessageDialog(null,"��˫���˺���ͷ�񣬴�ʱӦ�õ���һ������Ի�����");

                    }
                    else//����Ҷ�ӽڵ�
                    {
                    }
                }
            }

            public void mouseEntered(MouseEvent e)
            {


            }

            public void mouseExited(MouseEvent e)
            {
            }

            public void mousePressed(MouseEvent e)
            {
                final JTree tree = (JTree) e.getSource();
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

                if (selRow != -1)//�ж�ѡ�з�
                {
                    final Object node = selPath.getLastPathComponent();//ͨ��·����ָ��ָ��ýڵ�
                    if (e.getModifiers() == InputEvent.BUTTON3_MASK && !((IconNode) node).isLeaf())
                    {
                        JPopupMenu popup = new JPopupMenu();
                        JMenuItem refresh = new JMenuItem("ˢ�º����б�");
                        popup.add(refresh);

                        JMenuItem showOnLine = new JMenuItem("��ʾ������ϵ��");
                        popup.add(showOnLine);
                        popup.addSeparator();

                        JMenuItem hide = new JMenuItem("����Ը÷���ɼ�");
                        popup.add(hide);


                        JMenuItem online = new JMenuItem("���߶Ը÷�������");
                        popup.add(online);
                        popup.addSeparator();

                        JMenuItem add = new JMenuItem("��ӷ���");
                        popup.add(add);
                        add.addActionListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                IconNode Root31 = new IconNode(" ");//������ڵ�
                                IconNode Root3 = new IconNode("δ����");//������ڵ�
                                Root3.add(Root31);//��������ڵ�
                                Root.add(Root3);
                                tree.updateUI();
//								    TODO
                            }

                        });

                        JMenuItem reName = new JMenuItem("������");
                        popup.add(reName);
                        add.setForeground(Color.red);
                        add.setBackground(Color.green);

                        JMenuItem delete = new JMenuItem("ɾ������");
                        popup.add(delete);
                        delete.setIcon(new ImageIcon(touxiang1));
                        delete.setForeground(Color.red);

                        JMenuItem manager = new JMenuItem("���ѹ���");
                        popup.add(manager);

                        popup.setForeground(Color.green);

                        popup.show(e.getComponent(), e.getX(), e.getY());

                    }
                    else if (e.getModifiers() == InputEvent.BUTTON3_MASK && ((IconNode) node).isLeaf())
                    {//�Ҽ�����/Ҷ�ӽڵ�

                        JPopupMenu popup = new JPopupMenu();
                        JMenuItem sendMessage = new JMenuItem("���ͼ�ʱ��Ϣ");
                        popup.add(sendMessage);

                        JMenuItem sendEmail = new JMenuItem("���͵����ʼ�");
                        popup.add(sendEmail);
                        popup.addSeparator();

                        JMenuItem check = new JMenuItem("�鿴����");
                        popup.add(check);
                        check.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                // TODO Auto-generated method stub
                                Enumeration it = friendInfoTable.elements();
                                String nodeQQ = ((IconNode) node).getQQ();
                                for (int i = 0; i < n; i++)
                                {

                                    UserInfoBean friend = (UserInfoBean) it.nextElement();
                                    String qq = Integer.toString(friend.getQq());
                                    if (nodeQQ.equals(qq))
                                    {

                                        new InformationFrm(friend);
                                    }
                                }

                            }
                        });

                        JMenu chatRecord = new JMenu("��Ϣ��¼");
                        /******************************��Ϣ��¼�Ķ����Ӳ˵�begin****************************/
                        JMenuItem local = new JMenuItem("�鿴������Ϣ");
                        JMenuItem manyou = new JMenuItem("�鿴������Ϣ");
                        JMenuItem shangchuan = new JMenuItem("�鿴�ϴ���Ϣ");

                        chatRecord.add(local);
                        chatRecord.add(manyou);
                        chatRecord.add(shangchuan);

/******************************��Ϣ��¼�Ķ����Ӳ˵�end****************************/


                        popup.add(chatRecord);
                        popup.addSeparator();

                        JMenu setAuthority = new JMenu("����Ȩ��");
/******************************����Ȩ�޵Ķ����Ӳ˵�begin****************************/
                        JMenuItem pingbi = new JMenuItem("���δ�����Ϣ");
                        JMenuItem OH = new JMenuItem("���߶�������");
                        JMenuItem HO = new JMenuItem("�������ɼ�");
                        JMenuItem QX1 = new JMenuItem("ȡ������ɼ�");
                        JMenuItem QX2 = new JMenuItem("ȡ����������");

/******************************����Ȩ�޵Ķ����Ӳ˵�end****************************/
                        setAuthority.add(pingbi);
                        setAuthority.add(OH);
                        setAuthority.add(HO);
                        setAuthority.add(QX1);
                        setAuthority.add(QX2);
                        popup.add(setAuthority);

                        JMenuItem reName = new JMenuItem("�޸ı�ע����");
                        popup.add(reName);

                        JMenu move = new JMenu("�ƶ���ϵ����");
/******************************�ƶ���ϵ�����Ķ����Ӳ˵�begin****************************/
                        JMenuItem friend = new JMenuItem("�ҵĺ���");
                        JMenuItem strager = new JMenuItem("İ����");
                        JMenuItem black = new JMenuItem("������");

/******************************�ƶ���ϵ�����Ķ����Ӳ˵�end****************************/
                        move.add(friend);
                        move.add(strager);
                        move.add(black);

                        popup.add(move);
                        move.setIcon(new ImageIcon(touxiang1));
                        move.setForeground(Color.red);

                        JMenuItem delete = new JMenuItem("ɾ������");
                        delete.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e)
                            {

                                // TODO Auto-generated method stub
                                ClientToServer cts = new ClientToServer();
                                new Thread(cts).start();


                                TreePath path = tree.getSelectionPath();//��ȡѡ�нڵ�·��
                                Object node = path.getLastPathComponent();//ͨ��·����ָ��ָ��ýڵ�
                                String nodeQQ = "10";
                                if (((IconNode) node).isLeaf())//����ýڵ���Ҷ�ӽڵ�

                                {
                                    System.out.println("Ҷ�ӽڵ������Ϊ��" + ((IconNode) node).getText() + "QQΪ��" + ((IconNode) node).getQQ());
                                    nodeQQ = ((IconNode) node).getQQ();
                                }
                                if (nodeQQ.equals("10"))
                                {
                                    JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ĺ���!");
                                }

                                tree.updateUI();


                                int qq = MainFrm.userInfo.getQq();
                                int fqq = Integer.parseInt(nodeQQ);
                                cts.deleteFriend(qq, fqq);
                                JOptionPane.showMessageDialog(null, "������ɾ��!");

                                cts.closeConnect1();

                                //  JOptionPane.showMessageDialog(null, nodeQQ);

                            }

                        });
                        popup.add(delete);


                        JMenuItem report = new JMenuItem("�ٱ����û�");
                        popup.add(report);

                        JMenu manager = new JMenu("���ѹ���");
/******************************���ѹ���Ķ����Ӳ˵�begin****************************/
                        JMenuItem introduce = new JMenuItem("�����Ƽ�����");
                        JMenuItem guanzhu = new JMenuItem("��ע�˺���");
                        JMenuItem tixing = new JMenuItem("������������");
                        JMenuItem FM = new JMenuItem("���ѹ�����");
                        JMenuItem KJ = new JMenuItem("���������ݷ�ʽ");
/******************************���ѹ���Ķ����Ӳ˵�end****************************/
                        manager.add(introduce);
                        manager.add(guanzhu);
                        manager.add(tixing);
                        manager.add(FM);
                        manager.add(KJ);

                        popup.add(manager);
                        popup.addSeparator();

                        JMenu QMF = new JMenu("��Ա��ݹ���");
/******************************��Ա��ݹ��ܵĶ����Ӳ˵�begin****************************/
                        JMenuItem FM1 = new JMenuItem("���ö�̬����");
                        JMenuItem FM2 = new JMenuItem("���ú�������");
                        JMenuItem FM3 = new JMenuItem("����QQװ��");
                        QMF.addSeparator();
                        JMenuItem FM4 = new JMenuItem("��ԱȺ��Ȩ");
                        JMenuItem FM5 = new JMenuItem("Ⱥ��¡");
                        JMenuItem FM6 = new JMenuItem("���ѿ�¡");
                        JMenuItem FM7 = new JMenuItem("��������֪ͨ");
                        QMF.addSeparator();
                        JMenuItem FM8 = new JMenuItem("��ͨ��Ա");
                        JMenuItem FM9 = new JMenuItem("��Ҫ��Ա");


/******************************��Ա��ݹ��ܵĶ����Ӳ˵�end****************************/
                        QMF.add(FM1);
                        QMF.add(FM2);
                        QMF.add(FM3);
                        QMF.add(FM4);
                        QMF.add(FM5);
                        QMF.add(FM6);
                        QMF.add(FM7);
                        QMF.add(FM8);
                        QMF.add(FM9);

                        popup.add(QMF);

                        JMenuItem enterQQZone = new JMenuItem("����QQ�ռ�");
                        popup.add(enterQQZone);

                        JMenuItem enterBlog = new JMenuItem("������Ѷ΢��");
                        popup.add(enterBlog);

                        popup.setForeground(Color.green);

                        popup.show(e.getComponent(), e.getX(), e.getY());
                    }

                }
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        });
        tree.setBounds(20, 20, 300, 600);
        tree.setLocation(10, 300);
        tree.setRootVisible(false); //������ʾ���ڵ�
        tree.putClientProperty("JTree.lineStyle", "None");

        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);//��UIManager����ʹJTabbedPane���͸��
        JLabel Lfrind = new JLabel("����");
        Lfrind.setOpaque(false);
        JLabel Lzone = new JLabel("�ռ�");
        Lzone.setBackground(Color.red);
        Lzone.setOpaque(false);
        JLabel Lgroup = new JLabel("Ⱥ��");
        Lgroup.setOpaque(false);
        JLabel Lblog = new JLabel("΢��");
        Lblog.setOpaque(false);
        JLabel Llast = new JLabel("�����ϵ��");
        Llast.setOpaque(false);
        JTabbedPane tabeld = new JTabbedPane();
//		�����б�


        JScrollPane JSPane;        //�������
        JSPane = new JScrollPane(tree);
        JSPane.setOpaque(false);
        JSPane.getViewport().setOpaque(false);
        JSPane.setBackground(new Color(1, 1, 1, 44));

        tabeld.addTab("", JSPane);
        tabeld.setIconAt(0, new ImageIcon(Pskin1));
        tabeld.addTab("�ռ�", Lzone);
        tabeld.addTab("Ⱥ��", Lgroup);
        tabeld.addTab("΢��", Lblog);
        tabeld.addTab("�����ϵ��", Llast);
        tabeld.setBounds(0, 130, MainFrm.getBounds().width + 2, Mheight - 200);
        tabeld.setBackground(new Color(0, 0, 0, 0));
        return tabeld;
    }

    public void UpdateFriendList() throws IOException
    {

        tabeld = createFriendList(cts, userInfo, friendInfoTable, n);
        abc.add(tabeld);
        //	MainFrm.add(abc);
    }

    public void Update()
    {
        cts.Update();
    }

    public void Update1(Hashtable friendInfoTable, int n) throws IOException
    {
        if (tabeld != null)
        {
            Root.removeAllChildren();
            this.friendInfoTable = friendInfoTable;
            System.out.println("���´��������б�");
            tabeld = createFriendList(cts, userInfo, this.friendInfoTable, n);
            abc.add(tabeld);
        }

    }

    public void UpdateFriendList(Hashtable friendInfoTable, int n) throws IOException
    {

        Enumeration it = friendInfoTable.elements();


        int count = Root.getChildCount();
        //JOptionPane.showMessageDialog(null, ""+count);
        for (int i = 0; i < count; i++)
        {
            IconNode node = (IconNode) Root.getChildAt(i);
            int count2 = node.getChildCount();
            //JOptionPane.showMessageDialog(null, ""+count2);
            for (int j = 0; j < count2; j++)
            {
                IconNode nodechild = (IconNode) node.getChildAt(j);
                String[] str = nodechild.getText();
                int lstr = str[0].indexOf("(");
                int rstr = str[0].indexOf(")");
                String qqStr = str[0].substring(lstr + 1, rstr);
                //	JOptionPane.showMessageDialog(null, qqStr);
                UserInfoBean friend = (UserInfoBean) it.nextElement();
                String ss = friend.getStatus() ? "����" : "����";
                //	JOptionPane.showMessageDialog(null, friend.getQq()+":"+ss);
                String strqq = "" + friend.getQq();
                if (qqStr.equals(strqq))
                {
                    if (friend.getStatus())
                    {
                        face = "Image/MainIcon/qqicons\\Catch0000" + friend.getPhotoID() + ".jpg";
                        nodechild.setIcon(new ImageIcon(face));
                        //JOptionPane.showMessageDialog(null, friend.getQq()+"������");
                    }
                    else
                    {
                        face = "Image/MainIcon/qqicons\\Catch0000" + friend.getPhotoID() + ".jpg";
                        nodechild.setIcon(ColorConvertOp.getGrayPicture(face));
                    }

                }
            }
        }
    }


}
