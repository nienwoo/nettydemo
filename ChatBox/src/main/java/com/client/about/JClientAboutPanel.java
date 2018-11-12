package com.client.about;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.client.business.resourceManager.ResourceManager;

public class JClientAboutPanel extends JPanel
{

    /**
     * @Fields serialVersionUID:TODO(��һ�仰�������������ʾʲô)
     */
    private static final long serialVersionUID = 1L;
    String[] sfile = {}; // ���ļ��ж������ַ�������
    String[] s; // ��ʾ�ַ�������
    int i = 0; // ѭ������
    int y = 0; // ������ʾʱ �����ƶ�
    int num = 0; // ��¼�ַ������� �ڼ����ַ���
    int number = 0;// ��¼��ǰ��ӡ�����ָ��� ÿ����20������ ������ɫ
    int[] R = {0, 255, 128};
    int[] G = {255, 0, 128};
    int[] B = {255, 128, 0};
    Timer timer = new Timer();
    // public SoundManager music = new SoundManager();
    Color c;
    String bk = "bk";// ����ͼƬ

    /**
     * @Fields serialVersionUID:TODO(��һ�仰�������������ʾʲô)
     */

    public JClientAboutPanel()
    {

        ReadFile rf = new ReadFile(); //
        sfile = rf.readFile("1.txt"); //��ȡ�ļ� �����ַ������� ÿ��һ���ַ���
        s = new String[sfile.length]; //����һ�����ļ����ַ������鳤��һ�����ַ������飬Ϊ��ʾ��ʹ��
        for (int j = 0; j < sfile.length; j++)
        {
            s[j] = sfile[j]; //ÿ���ַ������鸳ֵΪ���ַ�
        }
        timer.schedule(new TimerTask()
        {
            /*
             * (non-Javadoc) <p>Title: run</p> <p>Description: </p>
             *
             * @see java.util.TimerTask#run()
             */
            @Override
            public void run()
            {

                display(); // ÿ���һ�������ػ�һ�� (�ﵽ��ӡ����Ч��)
                y -= 1;
                System.out.println(y);
                if (y < -300)
                {
                    y = 200;
                }

            }

        }, 100, 10);
        this.setVisible(true);

    }

    /*
     * (non-Javadoc) <p>Title: paintComponent</p> <p>Description: </p>
     *
     * @param g
     *
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g)
    {
        drawBackGround(g);
        drawText(g);
    }

    /*
     * ��������
     */
    public void drawText(Graphics g)
    {
        number++;
        int i = number % 3;


        //	R = 0;
        //	G = 255;
        //	B = 255;
        c = new Color(255, 0, 0);
        g.setFont(new java.awt.Font("����", 0, 20));
        for (int j = 0; j < sfile.length; j++)
        {

            if (s[j].length() != 0)
            {
                g.setColor(c);
                g.drawString(s[j], 20, (20 + j * 30) + y);
            }
            else
                continue;

        }
    }

    public void display()
    {
        this.repaint();
    }

    /*
     * ���Ʊ���ͼƬ
     */
    public void drawBackGround(Graphics g)
    {
        ResourceManager imageResource = new ResourceManager();
        ImageIcon icon = new ImageIcon();
        icon = imageResource.GetImage(bk);
        // TODO:����������ѭ���ƶ�

        // ���ƴ���
        g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height,
                this);

    }
}
