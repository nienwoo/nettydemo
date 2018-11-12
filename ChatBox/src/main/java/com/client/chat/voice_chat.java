package com.client.chat;

import java.awt.Event;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class voice_chat
{
    Socket client;
    InputStream in_Stream;   // ������
    OutputStream out_Stream; // �����
    BufferedReader read_in;
    BufferedWriter write_out;
    boolean flag;         //  ���ݶ�ȡ�Ƿ�


    public voice_chat()
    {
        // Socket ����1 String host
        //        ����2 int port
        try
        {
            client = new Socket("127.0.0.1", 5555);
            in_Stream = client.getInputStream();   // �����Ƶ��
            read_in = new BufferedReader(new InputStreamReader(in_Stream));
            out_Stream = client.getOutputStream();   // �����
            write_out = new BufferedWriter(new OutputStreamWriter(out_Stream));

        } catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // ��õ�����
        while (true)
        {
            try
            {

                byte[] buf = new byte[200];
//	    	 if(){
                in_Stream.read(buf); // �������������
//	    	 }
//	    	 else

            } catch (IOException e)
            {

                System.out.print(e.getMessage());
            }

        }
    }

    // �жϰ����¼�
    public boolean action(Event evt, Object arg)
    {
        if (evt.target.equals("����"))
        {
            try
            {
                //  Socket cli=new Socket("127.0.0.1",6000);  ////////////�˿ں�
                String ip = "127.0.0.1";
                //        Gain_Voice cap=new Gain_Voice(ip);
                //        cap.start();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

// Test
// public static void main(String[] args){
// voice_chat = new voice_chat();
// }   	

}
