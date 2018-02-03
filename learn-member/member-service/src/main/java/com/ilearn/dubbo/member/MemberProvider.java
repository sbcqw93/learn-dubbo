package com.ilearn.dubbo.member;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by George on 2018/2/3 0003.
 */
public class MemberProvider {
    public static void main(String[] args) {
        String[] configLocations = {"applicationcontext.xml"};
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        //context.start();
        System.out.print("dddd");
    }
}
