package com.client.transform;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.client.business.uiManager.ChatPanel;
import com.common.Message;
import com.tools.ClientToServerThread;

public class ServerTest extends JFrame
{

    public ChatPanel cp;

    JButton transformButton;
    JFrame mainframe;
    private JFileChooser fc;
    int flag;
    //	String filePath = "D:\\lib.rar";
    String filePath;
    boolean isReceive = false;

    int port = 8821;
    ClientToServerThread ctsT = null;
    Message msg = null;

    public ServerTest(ChatPanel cp, Message msg, ClientToServerThread ctsT)
    {
        this.cp = cp;
        this.msg = msg;
        this.ctsT = ctsT;

        fc = new JFileChooser();
        mainframe = this;
        //��ʼ������
        this.setBounds(300, 200, 400, 300);

        this.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ����ť
        transformButton = new JButton("�����ļ�");
        transformButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                chuanshu_do();
            }
        });
        transformButton.setBounds(0, 0, 160, 40);
        transformButton.setLocation(this.getWidth() / 2, this.getHeight() / 2);

        transformButton.setVisible(true);
        this.add(transformButton);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//	this.setVisible(true);
        chuanshu_do();
        this.setVisible(false);
    }

    public void chuanshu()
    {
        fc.setDialogTitle("��ѡ��Ҫ������ļ�");
        //������ʾ���ļ��ĶԻ���
        try
        {
            flag = fc.showOpenDialog(mainframe);
        } catch (HeadlessException head)
        {
            System.out.println("Open File Dialog ERROR!");
        }
        //�������ȷ����ť�����ø��ļ���
        if (flag == JFileChooser.APPROVE_OPTION)
        {
            //��ø��ļ�
            // cp.bt_File_Send();
            File f = fc.getSelectedFile();
            System.out.println("open file----" + f.getName() + "  filePath:" + f.getPath());
            filePath = f.getPath();

            start();        //��ʼ����

        }
    }

    void start()
    {
        Socket s = null;
        try
        {
            ServerSocket ss = new ServerSocket(port);
            while (true)
            {
                // ѡ����д�����ļ�

                File fi = new File(filePath);

                if (!isReceive)
                {
                    ctsT.sendData(msg);
                    ctsT.sendData(msg);
                    isReceive = true;

                }

                System.out.println("�ļ�����:" + (int) fi.length());

                // public Socket accept() throws
                // IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������

                s = ss.accept();
                System.out.println("����socket����");
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(s.getInputStream()));
                dis.readByte();


                DataInputStream fis = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(s.getOutputStream());
                // ���ļ��������ȴ����ͻ��ˡ�����Ҫ������������ƽ̨�������������Ĵ�������Ҫ�ӹ���������Բμ�Think In Java
                // 4th�����ֳɵĴ��롣
                ps.writeUTF(fi.getName());
                ps.flush();
                ps.writeLong((long) fi.length());
                ps.flush();

                int bufferSize = 8192;
                byte[] buf = new byte[bufferSize];

                while (true)
                {
                    int read = 0;
                    if (fis != null)
                    {
                        read = fis.read(buf);
                    }

                    if (read == -1)
                    {
                        break;
                    }
                    ps.write(buf, 0, read);
                }
                ps.flush();
                // ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ�����
                // ֱ��socket��ʱ���������ݲ�������
                fis.close();

                ps.close();
                dis.close();
                s.close();
                System.out.println("�ļ��������");

                //System.exit(1);
                this.dispose();
                return;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void chuanshu_do()
    {
        new send().run();


    }

    class send implements Runnable
    {
        public send()
        {
        }

        public void run()
        {
            if (!isReceive)
                chuanshu();
            else
            {
                return;
            }
        }
    }

    public static void main(String arg[])
    {
        //	new ServerTest();
    }

}
