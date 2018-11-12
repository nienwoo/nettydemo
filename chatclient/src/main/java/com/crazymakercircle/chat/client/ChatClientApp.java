package com.crazymakercircle.chat.client;

import com.crazymakercircle.chat.common.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//自动加载配置信息
@EnableAutoConfiguration
//使包路径下带有@Value的注解自动注入
//使包路径下带有@Autowired的类可以自动注入
@ComponentScan("com.crazymakercircle.chat.client")
@SpringBootApplication
public class ChatClientApp
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 启动并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext context = SpringApplication.run(ChatClientApp.class, args);
        ChatClient chatClient = context.getBean(ChatClient.class);

        User user=new User();

        user.setPlatform(User.PLATTYPE.WINDOWS);
        user.setUid("1");
        user.setNickName("maker-1");
        user.setToken("auth token");
        user.setDevId("apple 8 plus");
        user.setSessionId("session-id-1000001");
        chatClient.setUser(user);

        chatClient.run();
    }


}
