package com.crazymakercircle.nettydemo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//自动加载配置信息
@EnableAutoConfiguration
//使包路径下带有注解的类可以使用@Autowired自动注入
@ComponentScan("com.crazymakercircle.nettydemo")
@SpringBootApplication
public class ClientApp
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext context = SpringApplication.run(ClientApp.class, args);
        EchoClient echoClient = context.getBean(EchoClient.class);
        echoClient.run();
    }


}
