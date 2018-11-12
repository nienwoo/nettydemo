package com.client.chat;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * Title:        VoiceChat
 * Description:  ��Ƶ��׽
 */

public class Gain_Voice implements Runnable
{

    TargetDataLine line; //����Ƶ�豸��ȡ�����ݵ�������
    Thread thread;
    Socket s;
    String ip;//////
    BufferedOutputStream captrueOutputStream;

    public Gain_Voice(String ip)
    {  //������ ȡ��socket�Ի�����������

        this.ip = ip;

    }

    public void start()
    {

        thread = new Thread(this);
        thread.setName("Gain_Voice");
        thread.start();
    }

    public void stop()
    {
        thread = null;
    }

    public void run()
    {


        try
        {
            //��������� �˴����Լ���ѹ��������ѹ������
            // ServerSocket s1 = new ServerSocket(6000);
            //	  s = s1.accept();

            s = new Socket(ip, 6000);
            captrueOutputStream = new BufferedOutputStream(s.getOutputStream());
        } catch (IOException ex)
        {


            return;
        }
        // AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian��
        // sampleRate ÿ���������
        // sampleSizeInBits ÿ�������е�λ��
        // channels ��������������1��,������2��,�ȵȣ�
        // signed ָʾ�������з��ŵģ������޷��ŵ�
        // bigEndian ָʾ�Ƿ��� big-endian �ֽ�˳��洢���������е����ݣ�false ��ζ�� little-endian����
        AudioFormat format = new AudioFormat(8000, 16, 2, true, true);
        //������Ƶ
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        try
        {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());// �򿪾���ָ����ʽ�����󻺳�����С����
        } catch (Exception ex)
        { //��ý��ʺ������е��ڲ�����������������ֽ���
            return;              //�������Ĵ�С getBufferSize()
        }

        byte[] data = new byte[1024];//�˴���1024����������е�����Ӧ�������1024Ӧ����һ��
        int numBytesRead = 0;
        line.start();//����ĳһ������ִ������I/O

        while (thread != null)
        {
            numBytesRead = line.read(data, 0, 128);//ȡ���ݣ�1024���Ĵ�Сֱ�ӹ�ϵ��������ٶȣ�һ��ԽСԽ�죬
            try
            {
                captrueOutputStream.write(data, 0, numBytesRead);//д��������
            } catch (Exception ex)
            {
                break;
            }
        }

        line.stop(); //ֹͣ����Ӧ��ֹͣI/O�
        line.close();//�ر���,ָʾ�����ͷŵĸ���ʹ�õ�����ϵͳ��Դ
        line = null;

        try
        {
            captrueOutputStream.flush();
            captrueOutputStream.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}