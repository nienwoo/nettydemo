package com.client.chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.sound.sampled.AudioFormat;



public class phone_Chat
{

    public AudioFormat format;
    public Socket cc;
    public InputStream in;
    private String address;
    private VoicePlay play;

    public phone_Chat(String address)
    {
        this.address = address;
        startPhone();
    }

    // ��ʼ��Ӳ�����������պͲ�����
    public void iniAudioHardware() throws Exception
    {


    }

    public void startPhone()
    {
        try
        {
            iniAudioHardware();
            cc = new Socket(address, 9987); // �˿ں�
            in = cc.getInputStream();
            play = new VoicePlay();
            play.play();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {

            cc.close();
            in.close();
            play.stop();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // ���Ž��յ���Ƶ
    class VoicePlay
    {
        @SuppressWarnings("unused")
        private boolean complete = false;
        private byte[] gsmdata = new byte[1000];
        private int numBytesRead = 0;

        @SuppressWarnings("static-access")
        public void play()
        {
            try
            {

            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
            complete = false;
            while ((!Thread.currentThread().interrupted()))
            {
                try
                {
                    numBytesRead = in.read(gsmdata);
                    if (numBytesRead == -1)
                    {
                        complete = true;
                        break;
                    }

                } catch (IOException e)
                {
                    System.exit(1);
                }
            }
        }

        public void run()
        {
            play();
        }

        public void stop()
        {
            complete = true;
        }
    }
}
