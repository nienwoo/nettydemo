/**
 *
 */
package com.common;

/**
 * @author lenovo
 */
public class FriendsInfoBean
{

    private int fqq;             //����QQ��
    private String nickName;     //�����ǳ�
    private String sign;         //�����ǳ�
    private String subGroupName; //������������

    public FriendsInfoBean()
    {
    }

    public void setFqq(int fqq)
    {
        this.fqq = fqq;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public void setSubGroupName(String subGroupName)
    {
        this.subGroupName = subGroupName;
    }
    /////////////////////////////////////////////////////

    public int getFqq()
    {
        return fqq;
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getSign()
    {
        return sign;
    }

    public String getSubGroupName()
    {
        return subGroupName;
    }

}
