package com.client.about;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile
{
    public int line = 0;

    public int getLine()
    {
        //	ReadFile(fileName);
        return line;
    }

    public String[] readFile(String fileName)
    {
        //�ӵ�ǰimages/file�¶�ȡ�ļ�
        fileName = "file/" + fileName;
        String[] string = new String[1024];  //��ʼ�����ܶ���ַ�������
        File file = new File(fileName);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            //һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null)
            {
                //��ʾ�к�
                string[line] = tempString;
                //	System.out.println("line " + line + ": " + string[line]);
                line++;
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e1)
                {
                }
            }
        }
        //����������ַ�������
        String[] str = new String[line];
        for (int i = 0; i < line; i++)
        {
            str[i] = string[i];
        }
        return str;
    }
}
