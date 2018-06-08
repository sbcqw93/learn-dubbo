package com.ilearn.dubbo.appframework.support;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author George 2015-5-28 上午9:42:36 <br>
 *         框架公共方法及常量
 */
public class FrameUtils {

	private static final char[] charsArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	/** 外部调用时,请使用localIp,而不是直接调用 getLocalIp()方法 */
	public final static String localIp = getLocalIp();

	/**
	 * 得到服务本机IP
	 * 
	 * @return
	 */
	public static String getLocalIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "127.0.0.1";
		}
	}

	/**
	 * 非空判断
	 * 
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> params) {
		return null == params || params.size() == 0;
	}

	/**
	 * 非空判断
	 * 
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		return null == list || list.size() == 0;
	}

	/**
	 * 判断字符是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 将Object转换成String对象
	 * 
	 * @param object
	 * @return 字符串
	 */
	public static String getO2S(Object object) {
		return object == null ? "" : object.toString();
	}

	/**
	 * 判断时间格式是否符合YYYY-DD-MM.<br>
	 * 
	 * @param date
	 *            时间
	 * @return true:表示合法,false:表示非法
	 */
	public static boolean isDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return false;
		}
		Pattern p = null;
		p = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
		Matcher matcher = p.matcher(date);
		if (matcher.find()) {
			int year = Integer.valueOf(matcher.group(1));
			if (year < 1)// 如果年小于1,则时间不合法
				return false;
			int month = Integer.valueOf(matcher.group(2));
			if (month > 12 || month < 1)// 如果月不在1~12间,则时间不合法
				return false;
			int day = Integer.valueOf(matcher.group(3));
			if (day > 31 || day < 1)// 如果天不在1~31间,则时间不合法
				return false;
			// 以上条件全部满足,则字符串为合法时间
			return true;
		}
		return false;
	}

	/**
	 * 得到keyName的值
	 * 
	 * @param request
	 * @return keyName的值
	 */
	public static String getRequestValue(HttpServletRequest request, String keyName) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return null;
		}
		String requestValue = null;
		for (Cookie cookie : cookies) {
			if (keyName.equals(cookie.getName())) {
				requestValue = cookie.getValue();
				break;
			}
		}
		return requestValue;
	}
	/**
	 *
	 * 判断传入参数是否为数字且大于0.<br>
	 *
	 * @param integer
	 *            数字
	 * @return true:是,false:否
	 */
	public static boolean isIntegerAndGt0(Integer integer) {
		if (null == integer || integer <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 生成指定长度的随机字符串
	 *
	 * @param randomStrLen 长度
	 * @return
	 */

	public static String getRandomStrLen(int randomStrLen) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < randomStrLen; i++) {
			buffer.append(charsArray[random.nextInt(charsArray.length)]);
		}
		return buffer.toString();
	}
}
