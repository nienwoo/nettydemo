package com.client.cut;

/**
 * �Ŵ�Ч��
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Magnifier extends JFrame
{
    private Container container = getContentPane(); // ������
    private int setCoordinateX; // �Ŵ�x����
    private int setCoordinateY;// �Ŵ�y����
    private int absoluteX;// ������x����
    private int absoluteY;// ������Y����
    private int relativeX;// ��갴��ʱ�����x����
    private int relativeY;// ��갴��ʱ�����x����
    private boolean mousePressedNow;// �������Ƿ��¡����������Ϊtrue������Ϊfalse
    private int magnifierSize = 100;// �Ŵ󾵳ߴ�
    public JMagnifierPanel magnifierPanel = new JMagnifierPanel(magnifierSize);// �Ŵ��������

    public Magnifier()
    {// ���캯��������һ���Ŵ󾵴���
        setUndecorated(true); // �����Ե

        //this.setBackground(Color.red);
        setResizable(false);
        container.add(magnifierPanel);
        addMouseListener(new MouseFunctions());
        addMouseMotionListener(new MouseMotionFunctions());
        updateSize(magnifierSize);
        this.setVisible(true);
        this.addMouseMotionListener(new MouseMotionListener()
        {

            @Override
            public void mouseMoved(MouseEvent e)
            {
                // Repaint();
                // TODO Auto-generated method stub
                setLocation((int) e.getLocationOnScreen().getX() + 20, (int) e.getLocationOnScreen().getY() + 20);
                magnifierPanel.setMagnifierLocation(e.getX(), e.getY());

                setVisible(true);
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                // TODO Auto-generated method stub

            }
        });
        this.setLayout(null);
        this.setBackground(Color.red);
        this.setForeground(Color.black);
        this.setBounds(0, 0, 200, 200);
        this.setVisible(false);
    }

    /**
     * ������ڵ� ��������������Ϊ��
     */
    public static void main(String arg[])
    {

        Magnifier magnifier = new Magnifier();
        magnifier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * ���´��� �Ŵ󾵳ߴ�
     */

    public void updateSize(int magnifierSize)
    {

        magnifierPanel.setMagnifierSize(magnifierSize + 100);
        setSize(magnifierSize + 100, magnifierSize + 100);
        validate(); // ���������ӿؼ�
    }

    private class MouseFunctions extends MouseAdapter
    {

        public void mousePressed(MouseEvent e)
        {
            if (e.getClickCount() == 1)
            {// �������������һ�£�˵����ס�˴���
                mousePressedNow = true;
                relativeX = e.getX();
                relativeY = e.getY();
            }
        }

        public void mouseReleased(MouseEvent e)
        {

            mousePressedNow = false;
        }
    }

    private class MouseMotionFunctions extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
            if (mousePressedNow == true)
            {// �����ʱ��갴���ˣ�˵������ק����

                absoluteX = Magnifier.this.getLocationOnScreen().x + e.getX();
                absoluteY = Magnifier.this.getLocationOnScreen().y + e.getY();
                setCoordinateX = absoluteX - relativeX;
                setCoordinateY = absoluteY - relativeY;
                magnifierPanel.setMagnifierLocation(setCoordinateX,
                        setCoordinateY);
                setLocation(setCoordinateX, setCoordinateY);

            }

        }

    }

}
