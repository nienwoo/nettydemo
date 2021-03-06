package com.crazymakercircle.chat.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//自动加载配置信息
@Configuration
@EnableAutoConfiguration
//使包路径下带有@Value的注解自动注入
//使包路径下带有@Autowired的类可以自动注入
@ComponentScan("com.crazymakercircle.chat.server")
@SpringBootApplication
public class DemoServerApp
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 启动并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext context = SpringApplication.run(DemoServerApp.class, args);
        DemoServer nettyServer = context.getBean(DemoServer.class);
        nettyServer.run();
    }

}
