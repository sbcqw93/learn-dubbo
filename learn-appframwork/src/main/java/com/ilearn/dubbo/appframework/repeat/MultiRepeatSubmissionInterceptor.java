package com.ilearn.dubbo.appframework.repeat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ilearn.dubbo.appframework.cache.CacheService;
import com.ilearn.dubbo.appframework.support.FrameUtils;

/**
 * @author George 2016-8-27 上午11:47:47 <br>
 *         多实例拦截处理
 */
public class MultiRepeatSubmissionInterceptor extends RepeatSubmission {

	private static Logger LOG = LoggerFactory.getLogger(MultiRepeatSubmissionInterceptor.class);

	@Autowired
	private CacheService cacheService;

	public Object checkRepeatSubmission(ProceedingJoinPoint point, MultiForbidRepeatAnno multiForbidRepeatAnno)
			throws Throwable {
		// 得到HttpServletRequest
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// 得到HttpSession
		HttpSession session = request.getSession();
		// 得到会话中的标识值
		String sessionValue = FrameUtils.getRequestValue(request, multiForbidRepeatAnno.sessionKey());
		// 得到令牌名称
		String tokenName = getTokenName(multiForbidRepeatAnno.tokenKey(), sessionValue);
		// 生成令牌
		if (multiForbidRepeatAnno.buildToken()) {
			String newToken = null;
			// 方式一
			// if (StringUtils.isEmpty(tokenStr)) {
			// newToken = AccessToken.getToken();
			// cacheService.put(tokenName, newToken);
			// } else {
			// newToken = tokenStr;
			// }

			// 方式一
			newToken = AccessToken.getToken();
			cacheService.put(tokenName, newToken);

			request.setAttribute(multiForbidRepeatAnno.tokenKey(), newToken);
		}

		// 根据令牌检查是否重复提交
		if (multiForbidRepeatAnno.checkRepeatSubmission()) {
			// 取得缓存的令牌值
			String tokenStr = cacheService.get(tokenName);
			// 得到请求的令牌值
			String token = request.getParameter(multiForbidRepeatAnno.tokenKey());
			// 提交请求令牌为空
			if (StringUtils.isEmpty(token)) {
				LOG.warn("{}##会员ID,{}={},提交请求令牌为空", new Object[] { "100000000000", multiForbidRepeatAnno.tokenKey(),
						sessionValue });
				session.setAttribute(repeatSubmissionState, "1000");
			}
			// 已重复提交
			else if (!token.equals(tokenStr)) {
				LOG.warn("{}##会员ID,{}={},已重复提交", new Object[] { "100000000000", multiForbidRepeatAnno.tokenKey(),
						sessionValue });
				session.setAttribute(repeatSubmissionState, "1001");
			}
			// 合法请求
			else {
				session.setAttribute(repeatSubmissionState, "1002");
			}
			// 清空令牌
			cacheService.remove(tokenName);
		}
		return point.proceed();
	}

}
