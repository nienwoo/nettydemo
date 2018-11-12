package com.client.cut;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class JMagnifierPanel extends JPanel

{

    private Image screenImage;
    private int magnifierSize;//�Ŵ󾵵ĳߴ�
    private int locationX;
    private int locationY;
    private Robot robot;

    //�Ŵ�ߴ�
    public JMagnifierPanel(int magnifierSize)
    {
        //	this.setOpaque(false);
        //this.setBackground(Color.red);

        try
        {
            robot = new Robot();

        } catch (AWTException e)
        {
            e.printStackTrace();
        }

        // ����Ļ

        screenImage = robot.createScreenCapture(new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height));
        this.magnifierSize = magnifierSize;

    }

    //���÷Ŵ󾵵�λ�� x����  y����
    public void setMagnifierLocation(int locationX, int locationY)
    {

        this.locationX = locationX;
        this.locationY = locationY;
        repaint();        // ע���ػ��ؼ�
    }

    //���÷Ŵ󾵵ĳߴ� magnifierSize �Ŵ󾵳ߴ�
    public void setMagnifierSize(int magnifierSize)
    {

        this.magnifierSize = magnifierSize;
    }

    public void paintComponent(Graphics g)
    {

        super.paintComponent((Graphics2D) g);
        // �ؼ��������
        g.drawImage(
                screenImage,                 // Ҫ����ͼƬ
                0,                    // Ŀ����εĵ�һ���ǵ�x����
                0,                    // Ŀ����εĵ�һ���ǵ�y����
                magnifierSize,                 // Ŀ����εĵڶ����ǵ�x����
                magnifierSize,                 // Ŀ����εĵڶ����ǵ�y����
                locationX + (magnifierSize / 4),     // Դ���εĵ�һ���ǵ�x����
                locationY + (magnifierSize / 4),    // Դ���εĵ�һ���ǵ�y����
                locationX + (magnifierSize / 4 * 3),     // Դ���εĵڶ����ǵ�x����
                locationY + (magnifierSize / 4 * 3),     // Դ���εĵڶ����ǵ�y����
                this);
    }

}