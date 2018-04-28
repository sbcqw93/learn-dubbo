package com.ilearn.dubbo.member;

import com.ilearn.dubbo.shop.service.ext.ShopExtService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

/**
 * Created by George on 2018/2/3 0003.
 */
public class MemberProvider {
    public static void main(String[] args) throws Exception {
        String[] configLocations = {"applicationcontext.xml"};
        try {
            String homeDir = System.getProperty("learn.member.home");
            Log4jConfigurer.initLogging(homeDir + "/log4j.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();

        ShopExtService shopExtService = (ShopExtService) context.getBean("shopExtService");

        int shopId = 2000;

        System.out.println(shopExtService.selectShopById(shopId));

        System.in.read();
    }
}
