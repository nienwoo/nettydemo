package com.client.transform;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ClientTest extends JFrame
{
    JFrame mainframe = null;
    private JFileChooser fc;
    int flag;
    private ClientSocket cs = null;
    private String ip = "192.16.137.3"; // ���óɷ�����IP
    private int port = 8821;
    private String sendMessage = "Windwos";
    //	String savePath = "E:\\";
    String savePath;            //��������ļ���·��
    JButton transformButton;
    public rece rec = null;
    public boolean isStop = false;


    public ClientTest(String ip)
    {
        this.ip = ip;

        //final ClientTest client=new ClientTest(ip);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ������
        this.setBounds(300, 200, 400, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ����ť
        transformButton = new JButton("�����ļ�");
        transformButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                jieshou_do();
            }
        });
        transformButton.setBounds(0, 0, 160, 40);
        transformButton.setLocation(this.getWidth() / 2, this.getHeight() / 2);

        transformButton.setVisible(true);
        this.add(transformButton);

        this.setVisible(true);

        fc = new JFileChooser();
        fc.setDialogTitle("ѡ������ļ������·��");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//ֻ��ѡ��Ŀ¼

    }

    public void jieshou()
    {
        //������ʾ���ļ��ĶԻ���
        try
        {
            flag = fc.showOpenDialog(mainframe);
        } catch (HeadlessException head)
        {
            System.out.println("Open File Dialog ERROR!");
        }
        //��ø��ļ�
        File f = fc.getSelectedFile();

        System.out.println("open file----" + f.getName() + "  filePath:" + f.getPath());
        savePath = f.getPath();
        //��ʼ������Ϣ
        try
        {
            if (createConnection())
            {
                sendMessage();
                getMessage();
            }

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    private boolean createConnection()
    {
        cs = new ClientSocket(ip, port);
        try
        {
            cs.CreateConnection();
            System.out.print("���ӷ������ɹ�!" + "\n");
            return true;
        } catch (Exception e)
        {
            System.out.print("���ӷ�����ʧ��!" + "\n");
            return false;
        }

    }

    private void sendMessage()
    {
        if (cs == null)
            return;
        try
        {
            cs.sendMessage(sendMessage);
        } catch (Exception e)
        {
            System.out.print("������Ϣʧ��!" + "\n");
        }
    }

    private void getMessage()
    {
        if (cs == null)
            return;
        DataInputStream inputStream = null;
        try
        {
            inputStream = cs.getMessageStream();
        } catch (Exception e)
        {
            System.out.print("������Ϣ�������\n");
            return;
        }

        try
        {
            // ���ر���·�����ļ������Զ��ӷ������˼̳ж�����

            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];
            int passedlen = 0;
            long len = 0;

            savePath += inputStream.readUTF();
            DataOutputStream fileOut = new DataOutputStream(
                    new BufferedOutputStream(new BufferedOutputStream(
                            new FileOutputStream(savePath))));
            len = inputStream.readLong();

            System.out.println("�ļ��ĳ���Ϊ:" + len + "\n");
            System.out.println("��ʼ�����ļ�!" + "\n");

            while (true)
            {
                int read = 0;
                if (inputStream != null)
                {
                    read = inputStream.read(buf);
                }
                passedlen += read;
                if (read == -1)
                {
                    break;
                }
                // �����������Ϊͼ�ν����prograssBar���ģ���������Ǵ��ļ������ܻ��ظ���ӡ��һЩ��ͬ�İٷֱ�
                System.out.println("�ļ�������" + (passedlen * 100 / len) + "%\n");
                fileOut.write(buf, 0, read);
            }
            System.out.println("������ɣ��ļ���Ϊ" + savePath + "\n");

            fileOut.close();
            this.dispose();

        } catch (Exception e)
        {
            System.out.println("������Ϣ����" + "\n");
            return;
        }
        //this.dispose();
        isStop = true;
        return;
    }

    public void jieshou_do()
    {
        rec = new rece();
        rec.run();
    }

    public class rece implements Runnable
    {
        public rece()
        {
        }

        public void run()
        {
            if (!isStop)
                jieshou();
            else
                return;
        }
    }

    public static void main(String arg[])
    {
        JFrame frame = new JFrame();
        JButton transformButton;
        String ip = "192.16.137.2";
        final ClientTest client = new ClientTest(ip);
        //��ʼ������
        frame.setBounds(300, 200, 400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ����ť
        transformButton = new JButton("�����ļ�");
        transformButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                client.jieshou_do();
            }
        });
        transformButton.setBounds(0, 0, 160, 40);
        transformButton.setLocation(frame.getWidth() / 2, frame.getHeight() / 2);

        transformButton.setVisible(true);
        frame.add(transformButton);

        frame.setVisible(true);
        //String ip = "192.16.137.3";
        new ClientTest(ip);
    }
}
