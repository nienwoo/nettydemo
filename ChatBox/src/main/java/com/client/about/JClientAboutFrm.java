package com.client.about;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JClientAboutFrm extends JFrame
{
    String[] sfile;
    String[] s;
    int i = 0;
    int y = 0;
    int num = 0;
    int number = 0;
    float R = 0.3f, G = 0.4f, B = 0.1f;
    Timer timer = new Timer();
    JClientAboutPanel gdpanel = new JClientAboutPanel();
    /**
     * @Fields serialVersionUID:TODO(��һ�仰�������������ʾʲô)
     */
    private static final long serialVersionUID = 1L;

    public JClientAboutFrm()
    {
        // setTitle("��Ϸ����");
        this.setBounds(20, 20, 400, 250);
        // ��Key�¼�ͬʱ��Ӹ�JPanel��JTextFieil���
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(gdpanel);
        this.setVisible(true);
    }

    public static void main(String[] agrs)
    {
        new JClientAboutFrm();
    }

    public void CloseWindow()
    {
        this.dispose();
    }

}
