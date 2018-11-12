/**
 *
 */
package com.tools;

import java.util.HashMap;

/**
 * @author lenovo
 */
public class ManageClientToServerThread
{

    private static HashMap hm = new HashMap<String, ClientToServerThread>();

    //�Ѵ����õ�ClientConServerThread���뵽hm
    public static void addClientConServerThread(String qqId, ClientToServerThread ccst)
    {
        hm.put(qqId, ccst);
    }

    //����ͨ��qqIdȡ�ø��߳�
    public static ClientToServerThread getClientConServerThread(String qqId)
    {
        return (ClientToServerThread) hm.get(qqId);
    }
}
