package com.client.cut;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.client.business.uiManager.ChatPanel;

public class Cut extends JFrame
{
    Magnifier mf = new Magnifier();

    JMagnifierPanel magnifierPanel = new JMagnifierPanel(100);
    ;
    private String save_type[] = {".png", ".jpg", ".bmp", "gif"}; // �����ͼ������
    private String[] save_type_de = {"PNG (*.png)", "JPG (*.jpg)",
            "BMP (*.bmp)", "GIF (*.gif)"};
    private Container container = getContentPane(); // ������
    private static final long serialVersionUID = 1L;
    int startx, starty, endx, endy;// ��갴�º��ͷ�ʱx��y������
    int sx, sy;
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();// �����Ļ��С
    BufferedImage image;// �洢������Ļ
    BufferedImage tempImage;// ����
    BufferedImage saveImage;// ����(��ȡ������)
    Graphics g;

    ChatPanel cp;
    boolean flag = false;
    CutToolbarJWindow ctjw;

    public void cutSc()
    {
        try
        {
            Robot robot = new Robot();// �ڻ�����Ļ����ϵ�й���һ�� Robot����
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            image = robot.createScreenCapture(new Rectangle(0, 0, d.width,
                    d.height));// ���������Ļ
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g)
    {
        RescaleOp ro = new RescaleOp(0.8f, 0, null);// ����һ��������ϣ�����������Ӻ�ƫ����
        tempImage = ro.filter(image, null);// ��Դ BufferedImage����image����������
        g.drawImage(tempImage, 0, 0, this);
    }

    // ���캯��
    public Cut(ChatPanel cp)
    {

        this.cp = cp;
        cp.setVisible(false);
        setUndecorated(true);
        mf.setVisible(false);
        container.add(magnifierPanel);
        cutSc(); // ����
        setResizable(false);
        setVisible(true);// ���ô��ڿɼ�

        setSize(d);// ��󻯴���


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲرշ�ʽ
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (flag == false)
                {
                    mf.setVisible(false);
                    sx = startx = e.getX();
                    sy = starty = e.getY();
                    if (ctjw != null)
                    {
                        ctjw.dispose();
                    }
                }

            }
        });
        this.addMouseMotionListener(new MouseMotionListener()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                // Repaint();
                // TODO Auto-generated method stub
                if (!flag)
                {
                    mf.setLocation(e.getX() + 5, e.getY() + 5);
                    mf.magnifierPanel.setMagnifierLocation(e.getX(), e.getY());
                    mf.setVisible(true);
                }


            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {

                if (flag == false
                        && e.getModifiers() == MouseEvent.BUTTON1_MASK)
                {
                    endx = e.getX();
                    endy = e.getY();
                    g = getGraphics();
                    g.drawImage(tempImage, 0, 0, Cut.this);
                    int x = Math.min(startx, endx);
                    int y = Math.min(starty, endy);
                    int width = Math.abs(endx - startx) + 1; // ����1����ֹwidth��heightΪ0
                    int height = Math.abs(endy - starty) + 1;
                    g.setColor(Color.BLUE);
                    g.drawRect(x - 1, y - 1, width + 1, height + 1);// ��1����1����Ϊ�˷�ֹͼƬ�����ο򸲸ǵ�
                    saveImage = image.getSubimage(x, y, width, height);
                    g.drawImage(saveImage, x, y, Cut.this);

                }
            }
        });

        this.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            { // ��Esc���˳�
                if (e.getKeyCode() == 27)
                {
                    if (saveImage != null)
                    {
                        // saveToFile();//����ͼƬ
                    }
                    System.exit(0);// �˳�
                }
            }
        });

        this.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {// ����(˫��)���ʱ���� ���
                // TODO Auto-generated method stub
                if (e.getClickCount() == 2)
                {
                    if (saveImage != null)
                    {// �����ͼ���򱣴�ͼƬ������
                        // closeWindows();

						/*
                         * CutScreen cs = new CutScreen(saveImage, endx, endy);
						 * cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 * cs.setSize(saveImage.getWidth(),
						 * saveImage.getHeight()); cs.setVisible(true);
						 */

                    }

                }
                // flag= false;
                if (ctjw != null)
                {
                    ctjw.dispose();
                }
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK
                        && e.getClickCount() == 1)
                {

                    closeWindow();

                    // mf.setLocation(e.getX()+5, e.getY()+5);
                    // mf.magnifierPanel.setMagnifierLocation(e.getX(),
                    // e.getY());
                    mf.dispose();
                    // mf.setVisible(true);
                    //new Cut();
                    // flag = false;

                }

            }

            public void mouseEntered(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }

            public void mouseExited(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }

            public void mousePressed(MouseEvent e)
            {// �������Ҽ����˳�����
                // TODO Auto-generated method stub
				/*
				 * if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
				 * 
				 * // closeWindow(); Repaint(); //if(ctjw!=null) //{
				 * ctjw.dispose(); //} //new Cut(); }
				 */
            }

            public void mouseReleased(MouseEvent e)
            {
                // TODO Auto-generated method stub
                //

                if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
                {

                    if (endx - sx != saveImage.getWidth()
                            && endy - sy != saveImage.getHeight())
                    {
                        // if(ctjw!=null)
                        // ctjw.dispose();
                        flag = true;
                        mf.setVisible(false);
                        initCtb();
                        ctjw.setVisible(true);
                    }

                }

            }
        });

    }

    public Cut()
    {

        this.cp = cp;
        setUndecorated(true);
        mf.setVisible(false);
        container.add(magnifierPanel);
        cutSc(); // ����
        setVisible(true);// ���ô��ڿɼ�
        setSize(d);// ��󻯴���
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲرշ�ʽ
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (flag == false)
                {
                    mf.setVisible(false);
                    sx = startx = e.getX();
                    sy = starty = e.getY();
                    if (ctjw != null)
                    {
                        ctjw.dispose();
                    }
                }

            }
        });
        this.addMouseMotionListener(new MouseMotionListener()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                // Repaint();
                // TODO Auto-generated method stub
                if (!flag)
                {
                    mf.setLocation(e.getX() + 5, e.getY() + 5);
                    mf.magnifierPanel.setMagnifierLocation(e.getX(), e.getY());

                    mf.setVisible(true);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {

                if (flag == false
                        && e.getModifiers() == MouseEvent.BUTTON1_MASK)
                {
                    endx = e.getX();
                    endy = e.getY();
                    g = getGraphics();
                    g.drawImage(tempImage, 0, 0, Cut.this);
                    int x = Math.min(startx, endx);
                    int y = Math.min(starty, endy);
                    int width = Math.abs(endx - startx) + 1; // ����1����ֹwidth��heightΪ0
                    int height = Math.abs(endy - starty) + 1;
                    g.setColor(Color.BLUE);
                    g.drawRect(x - 1, y - 1, width + 1, height + 1);// ��1����1����Ϊ�˷�ֹͼƬ�����ο򸲸ǵ�
                    saveImage = image.getSubimage(x, y, width, height);
                    g.drawImage(saveImage, x, y, Cut.this);

                }
            }
        });

        this.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            { // ��Esc���˳�
                if (e.getKeyCode() == 27)
                {
                    if (saveImage != null)
                    {
                        // saveToFile();//����ͼƬ
                    }
                    System.exit(0);// �˳�
                }
            }
        });

        this.addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {// ����(˫��)���ʱ���� ���
                // TODO Auto-generated method stub
                if (e.getClickCount() == 2)
                {
                    if (saveImage != null)
                    {// �����ͼ���򱣴�ͼƬ������
                        // closeWindows();

						/*
						 * CutScreen cs = new CutScreen(saveImage, endx, endy);
						 * cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 * cs.setSize(saveImage.getWidth(),
						 * saveImage.getHeight()); cs.setVisible(true);
						 */

                    }

                }
                // flag= false;
                if (ctjw != null)
                {
                    ctjw.dispose();
                }
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK
                        && e.getClickCount() == 1)
                {

                    closeWindow();

                    // mf.setLocation(e.getX()+5, e.getY()+5);
                    // mf.magnifierPanel.setMagnifierLocation(e.getX(),
                    // e.getY());
                    mf.dispose();
                    // mf.setVisible(true);
                    new Cut();
                    // flag = false;

                }

            }

            public void mouseEntered(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }

            public void mouseExited(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }

            public void mousePressed(MouseEvent e)
            {// �������Ҽ����˳�����
                // TODO Auto-generated method stub
				/*
				 * if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
				 * 
				 * // closeWindow(); Repaint(); //if(ctjw!=null) //{
				 * ctjw.dispose(); //} //new Cut(); }
				 */
            }

            public void mouseReleased(MouseEvent e)
            {
                // TODO Auto-generated method stub
                //

                if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
                {

                    if (endx - sx != saveImage.getWidth()
                            && endy - sy != saveImage.getHeight())
                    {
                        // if(ctjw!=null)
                        // ctjw.dispose();
                        flag = true;
                        mf.setVisible(false);
                        initCtb();
                        ctjw.setVisible(true);
                    }

                }

            }
        });

    }


    public static void main(String[] args)
    {
        new Cut();

    }

    public void closeWindows()
    {
        this.dispose();
    }

    public void initCtb()
    {
        int x = endx, y = endy;
        if (endx < sx)
            x = sx;
        if (endy < sy)
            y = sy;
        if (x < 367)
            x = 367;
        if (y + 28 > 769)
        {
            y = y - saveImage.getHeight() - 28 - 6;
            if (sy - 28 < 0)
                y = 0;
        }

        ctjw = new CutToolbarJWindow(this, x + 5, y + 6);
    }

    public void insertCutImage()
    {
        Image i = saveImage;
        ImageIcon ic = new ImageIcon(i);

        cp.insertCutImage(ic);
    }

    public void closeWindow()
    {
        if (ctjw != null)
            ctjw.dispose();
        this.dispose();
        // this.closeWindow();

    }

    public void Repaint()
    {
        this.repaint();
    }

    // ������Ӧ
    public void Save()
    {


        closeWindow();
        String defaultDir = "c:/"; // ����Ĭ��·�� ΪC��
        JFileChooser jf = new JFileChooser(); // ��ö���
        jf.setDialogTitle("�����ļ�"); // �Զ���ѡ������
        jf.setSelectedFile(new File("my")); // ����Ĭ���ļ���

        // �򿪱���Ի���
        jf.setCurrentDirectory(new File(defaultDir));// ����Ĭ��Ŀ¼ ��ֱ��Ĭ��C��

        jf.setFileFilter(new FileNameExtensionFilter(save_type_de[0],
                save_type[0])); // �����ʽ����
        jf.setFileFilter(new FileNameExtensionFilter(save_type_de[1],
                save_type[1])); // �����ʽ����
        jf.setFileFilter(new FileNameExtensionFilter(save_type_de[2],
                save_type[2])); // �����ʽ����
        jf.setFileFilter(new FileNameExtensionFilter(save_type_de[3],
                save_type[3])); // �����ʽ����
        jf.showSaveDialog(null);
        File fi = jf.getSelectedFile(); //
        String path = fi.getPath(); // ·��
        String name = fi.getName(); // ����ļ���
        System.out.println(jf.getFileFilter().getDescription());
        System.out.println(jf.getFileFilter().hashCode());
        File f = null;
        for (int i = 0; i < 4; i++)
        {
            if (jf.getFileFilter().getDescription().equals(save_type_de[i]))
            {
                f = new File(path + "." + save_type[i]);
            }
        }
        System.out.println("save: " + f); // ��ʾ
        try
        {

            ImageIO.write(saveImage, "png", f);// ����ͼƬ

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
