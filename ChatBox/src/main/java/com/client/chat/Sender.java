package com.client.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class Sender
{
    /*����ip*/
    public static String localIP = "127.0.0.1";
    /*������ip*/
    public static String serverIP = null;
    /*������Ϣ*/
    public static String err_msg = "";
    /*Ĭ�Ϸ��Ͷ˿�*/
    public static int SendPort = 5555;
    /*Ĭ������˿�*/
    public static int chatPort = 6666;

    /*������Ϣ��socket*/
    /*private static DatagramSocket recDs = null;*/
	/*private static DatagramSocket chatSoc = null;*/
    static
    {
		/*rm = new Random();*/
		/*setLocalIP();*/
		/*chatPort = generateUDPPort();*/
    }

    public Sender()
    {
/*		try {
			if(recDs == null){
				rePort = generateUsefulPort();
				recDs = new DatagramSocket(rePort);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}*/
        //findServer();
    }

    /**
     * @param msgType    ��Ϣ���
     * @param uname
     * @param friendIP
     * @param friendPort
     * @return �����Ƿ�ɹ�
     */
    public static boolean sendUDPMsg(int msgType, String uname, String friendIP, int friendPort, String messae)
    {
        try
        {
            /*�������еõ�Ҫ���͵����ݣ�ʹ��UTF-8���뽫��Ϣת��Ϊ�ֽ�*/
            byte[] msg = (msgType + "*" + uname + "*" + messae).getBytes("UTF-8");
            /*�õ�������internet��ַ*/
            InetAddress address = InetAddress.getByName(friendIP);

            /*�����ݺ͵�ַ��ʼ��һ�����ݱ����飨���ݰ���*/
            DatagramPacket packet = new DatagramPacket(msg, msg.length, address,
                    friendPort);

            /*����һ��Ĭ�ϵ��׽��֣�ͨ�����׽��ַ������ݰ�*/
            DatagramSocket dSocket = new DatagramSocket();
            dSocket.send(packet);

            /*������Ϻ�ر��׽���*/
            dSocket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            err_msg = "ϵͳ�����쳣��";
            return false;
        }
        return true;
    }
    /**
     * ��ȡ������IP
     */
/*	private  static void setLocalIP(){
		try {
			InetAddress address = InetAddress.getLocalHost();
			localIP = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			err_msg = "����:SendMessage.takeLocalIP()��Exception��";
		}
		System.out.println("localIP = " + localIP);
	}*/
	/*private static Random  rm;*/
/*	private static int generateUsefulPort(){
			int port  = 1025 + rm.nextInt(5000);
			try{   
		        bindPort("0.0.0.0", port);   
		        bindPort(InetAddress.getLocalHost().getHostAddress(),port);   
		    }catch(Exception e){   
		    	e.printStackTrace();
		        return generateUsefulPort();   
		    }  
		    System.out.println("generateUsefulPort=" + port);
		    return port;
	}*/
/*	public static int generateUDPPort(){
		rePort = 1024 + rm.nextInt(5000);
		try {
			DatagramSocket socket = new DatagramSocket(rePort);
			socket.close();
		} catch (IOException e) {
			return generateUDPPort();
		}
		return rePort;
}*/
    /**
     * ���tcp�˿��Ƿ����
     * @param host
     * @param port
     * @throws Exception
     */
/*	private static void bindPort(String host, int port) throws Exception{   
        Socket s = new Socket();   
        s.bind(new InetSocketAddress(host, port));   
        s.close();
    }   */
}
