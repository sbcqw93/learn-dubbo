package com.ilearn.dubbo.appframework.base64;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author George 2017-12-12 下午1:55:33 <br>
 * 
 */
public class EncryptDataSourceFactory implements FactoryBean<Properties> {

	private Properties properties;
	private String key = "DB";
	private BASE64Encoder encoder = new BASE64Encoder();
	private BASE64Decoder decoder = new BASE64Decoder();

	public final static String DRIVER_MANAGER_USER_PROPERTY = "user";
	public final static String DRIVER_MANAGER_PASSWORD_PROPERTY = "password";

	@Override
	public Properties getObject() throws Exception {
		return this.properties;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setProperties(Properties properties) throws IOException {
		this.properties = properties;
		String originalUsername = properties.getProperty(DRIVER_MANAGER_USER_PROPERTY);
		String originalPassword = properties.getProperty(DRIVER_MANAGER_PASSWORD_PROPERTY);
		if (originalUsername != null) {
			String newUsername = this.decrypt(originalUsername);
			this.properties.put(DRIVER_MANAGER_USER_PROPERTY, newUsername);
		}
		if (originalPassword != null) {
			String newPassword = this.decrypt(originalPassword);
			this.properties.put(DRIVER_MANAGER_PASSWORD_PROPERTY, newPassword);
		}
	}

	/**
	 * 加密字符串(加码)
	 * 
	 * @param string
	 *            原字符串
	 * @return 加密后字符串
	 */
	public String encrypt(String string) throws IOException {
		if (string == null) {
			return string;
		}
		string = key.concat(":").concat(string);
		return encoder.encodeBuffer(string.getBytes());
	}

	/**
	 * 解密字符串(解码)
	 * 
	 * @param string
	 * @return
	 * @throws 解密后字符串
	 */
	public String decrypt(String string) throws IOException {
		if (string == null) {
			return string;
		}
		return new String(decoder.decodeBuffer(string)).replace(key.concat(":"), "");
	}
}
