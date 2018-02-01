package com.ilearn.dubbo.appframework.repeat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ilearn.dubbo.appframework.support.FrameUtils;

/**
 * @author George 2016-8-30 下午8:12:21 <br>
 *         单实例拦截处理
 */
public class SingleRepeatSubmissionInterceptor extends RepeatSubmission {

	private static Logger LOG = LoggerFactory.getLogger(SingleRepeatSubmissionInterceptor.class);

	public Object checkRepeatSubmission(ProceedingJoinPoint point, SingleForbidRepeatAnno singleForbidRepeatAnno)
			throws Throwable {
		// 得到HttpServletRequest
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// 得到HttpSession
		HttpSession session = request.getSession();
		// 得到会话中的标识值
		String sessionValue = FrameUtils.getRequestValue(request, singleForbidRepeatAnno.sessionKey());
		// 得到令牌名称
		String tokenName = getTokenName(singleForbidRepeatAnno.tokenKey(), sessionValue);
		// 生成令牌
		if (singleForbidRepeatAnno.buildToken()) {
			String newToken = null;
			// 方式一
			// if (StringUtils.isEmpty(tokenStr)) {
			// newToken = AccessToken.getToken();
			// session.setAttribute(tokenName, newToken);
			// } else {
			// newToken = tokenStr;
			// }

			// 方式二
			newToken = AccessToken.getToken();
			session.setAttribute(tokenName, newToken);

			request.setAttribute(singleForbidRepeatAnno.tokenKey(), newToken);
		}

		// 根据令牌检查是否重复提交
		if (singleForbidRepeatAnno.checkRepeatSubmission()) {
			// 取得Session的令牌值
			String tokenStr = FrameUtils.getO2S(session.getAttribute(tokenName));
			// 得到请求的令牌值
			String token = request.getParameter(singleForbidRepeatAnno.tokenKey());
			// 提交请求令牌为空
			if (StringUtils.isEmpty(token)) {
				LOG.warn("{}##会员ID,{}={},提交请求令牌为空", new Object[] { "100000000000", singleForbidRepeatAnno.tokenKey(),
						sessionValue });
				session.setAttribute(repeatSubmissionState, "1000");
			}
			// 已重复提交
			else if (!token.equals(tokenStr)) {
				LOG.warn("{}##会员ID,{}={},已重复提交", new Object[] { "100000000000", singleForbidRepeatAnno.tokenKey(),
						sessionValue });
				session.setAttribute(repeatSubmissionState, "1001");
			}
			// 合法请求
			else {
				session.setAttribute(repeatSubmissionState, "1002");
			}
			// 清空令牌
			session.removeAttribute(tokenName);
		}
		return point.proceed();
	}
}
