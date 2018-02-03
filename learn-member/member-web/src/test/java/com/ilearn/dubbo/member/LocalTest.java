package com.ilearn.dubbo.member;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * @author George 2014-11-12 下午4:54:28 <br>
 *         测试基类
 */
@SuppressWarnings("unchecked")
public abstract class LocalTest extends BaseTest {

	private ApplicationContext ctx;

	public void initBean() {
		String propertyKey = "learn.member.home";
		System.setProperty(propertyKey, "./config");

		try {
			String ep_home = System.getProperty(propertyKey);
			if (null != ep_home && "" != ep_home) {
				Log4jConfigurer.initLogging(ep_home + "/log4j.properties");
			}
			ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
			System.out.println("ctx => " + ctx);
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public <T> T getService(Class<T> c) {
		return ctx.getBean(c);
	}

	public <T> T getService(String bean) {
		return (T) ctx.getBean(bean);
	}

}
