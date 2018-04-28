package com.ilearn.dubbo.shop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
public class ShopProvider {
    public static void main(String[] args) throws Exception {
        String[] configLocations = {"applicationcontext.xml"};
        try {
            String homeDir = System.getProperty("learn.shop.home");
            Log4jConfigurer.initLogging(homeDir + "/log4j.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();

        System.in.read();
    }
}
