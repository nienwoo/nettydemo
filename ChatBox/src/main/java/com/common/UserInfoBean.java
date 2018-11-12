/**
 *
 */
package com.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lenovo
 */
public class UserInfoBean implements Serializable
{

    private int qq;               //QQ��
    private String pwd;           //����
    private String sign;          //ǩ��
    private int photoID;          //ͷ��ID
    private String nickname;      //�ǳ�
    private String sex;           //�Ա�
    private String birthday;        //��������
    private String constellation; //����
    private String bloodType;     //Ѫ��
    private String diploma;       //ѧ��
    private String telephone;     //�绰
    private String email;         //�����ʼ�
    private String address;       //��ַ

    private boolean status;       //�Ƿ��¼
    private String subGroupName; //������Ϣ
    private String groupName;    //Ⱥ��Ϣ
    private String IP;           //IP
    private int PORT;         //�˿�

    public UserInfoBean()
    {
    }

    public void setQq(int qq)
    {
        this.qq = qq;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public void setPhotoID(int photoID)
    {
        this.photoID = photoID;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public void setConstellation(String constellation)
    {
        this.constellation = constellation;
    }

    public void setBloodType(String bloodType)
    {
        this.bloodType = bloodType;
    }

    public void setDiploma(String diploma)
    {
        this.diploma = diploma;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setNickName(String nickName)
    {
        this.nickname = nickName;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void setSubGroupName(String subGroupName)
    {
        this.subGroupName = subGroupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public void setIP(String IP)
    {
        this.IP = IP;
    }

    public void setPort(int Port)
    {
        this.PORT = Port;
    }
    ///////////////////////////////////////////////

    public int getQq()
    {
        return qq;
    }

    public String getPwd()
    {
        return pwd;
    }

    public String getSign()
    {
        return sign;
    }

    public int getPhotoID()
    {
        return photoID;
    }

    public String getNickName()
    {
        return nickname;
    }

    public String getSex()
    {
        return sex;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getConstellation()
    {
        return constellation;
    }

    public String getBloodType()
    {
        return bloodType;
    }

    public String getDiploma()
    {
        return diploma;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public boolean getStatus()
    {
        return status;
    }

    public String getSubGroupName()
    {
        return subGroupName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public String getIP()
    {
        return IP;
    }

    public int getPort()
    {
        return PORT;
    }
}
