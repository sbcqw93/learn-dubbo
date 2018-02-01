package com.ilearn.dubbo.appframework.repeat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多实例
 * 
 * @author George 2016-8-23 下午5:32:25 <br>
 *         禁止重复提交注解<br>
 *         用于多实例服务，主要区别:<br>
 *         1、单实例，取sessionKey时将从HttpSession中取，请参考{@link SingleForbidRepeatAnno}<br>
 *         2、多实例，取sessionKey时将从cache中取，请参考{@link MultiForbidRepeatAnno}<br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiForbidRepeatAnno {
	/**
	 * 生成令牌：true表示生成，false表示不生成
	 * 
	 */
	boolean buildToken() default false;

	/**
	 * 检查是否重复提交：true表示检查，false表示不检查
	 * 
	 */
	boolean checkRepeatSubmission() default false;

	/**
	 * 会话ID的Key：默认是"sessionId"，如果不使用字符值，请传入正确的KEY值
	 * 
	 */
	String sessionKey() default "sessionId";

	/**
	 * 作为令牌的唯一标识，建议传入唯一的tokenKey，否则会导致多个请求共享一个令牌的现象<br>
	 * 注意：前台请求参数要传入此tokenKey与他的值
	 */
	String tokenKey();
}
