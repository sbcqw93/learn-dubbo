package com.ilearn.dubbo.appframework.repeat;

/**
 * @author George 2016-8-31 上午9:36:55 <br>
 *         重复提交基类
 */
public abstract class RepeatSubmission {

	/**
	 * 令牌根
	 */
	public static String tokenRoot = "tokenRoot";

	/**
	 * repeatSubmissionState=1000，表示“提交请求令牌为空”<br>
	 * repeatSubmissionState=1001，表示“已重复提交”<br>
	 * repeatSubmissionState=1002，表示合法提交请求
	 */
	public static String repeatSubmissionState = "repeatSubmissionState";

	/**
	 * 
	 * @param tokenKey
	 * @param sessionValue
	 * @return tokenRoot+tokenKey+sessionValue
	 */
	public String getTokenName(String tokenKey, String sessionValue) {
		return AccessToken.getTokenName(tokenKey, sessionValue);
	}

}
