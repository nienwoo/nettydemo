package com.crazymakercircle.chat.common.bean;

import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class User
{
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    String uid;
    String devId;
    String token;
    String nickName;
    PLATTYPE platform;
    private String sessionId;

    // windows,mac,android, ios, web , other
    public enum PLATTYPE
    {
        WINDOWS, MAC, ANDROID, IOS, WEB, OTHER;
    }



    public void setPlatform(int platform)
    {
        PLATTYPE[] values=   PLATTYPE.values();
        for (int i = 0; i < values.length; i++)
        {
            if(values[i].ordinal()==platform)
            {
                this.platform = values[i];
            }
        }

    }

    @Override
    public String toString()
    {
        return "User{" +
                "uid='" + uid + '\'' +
                ", devId='" + devId + '\'' +
                ", token='" + token + '\'' +
                ", nickName='" + nickName + '\'' +
                ", platform=" + platform +
                '}';
    }

    public static User build(ProtoMsg.LoginRequest info)
    {
        User user = new User();
        user.uid = new String(info.getUid());
        user.devId = new String(info.getDeviceId());
        user.token = new String(info.getToken());
        user.setPlatform(info.getPlatform());
        LOGGER.info("登录中: {}", user.toString());
        return user;

    }



}
