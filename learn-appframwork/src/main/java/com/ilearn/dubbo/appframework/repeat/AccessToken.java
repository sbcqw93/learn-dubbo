package com.ilearn.dubbo.appframework.repeat;

import com.ilearn.dubbo.appframework.cache.CacheService;
import com.ilearn.dubbo.appframework.support.FrameUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author George 2016-8-24 上午9:05:28 <br>
 *         生成Token
 */
public final class AccessToken {

    private CacheService cacheService;

    public AccessToken(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    /**
     * 得到一个token
     *
     * @return
     */
    public static String getToken() {
        long current = System.currentTimeMillis();
        long random = new Random().nextLong();
        StringBuffer buffer = new StringBuffer();
        buffer.append(current).append(Math.abs(random));
        return buffer.toString();
    }

    /**
     * 令牌名称
     *
     * @param tokenKey
     * @param sessionValue
     * @return tokenRoot+tokenKey+sessionValue
     */
    public static String getTokenName(String tokenKey, String sessionValue) {
        return new StringBuffer(RepeatSubmission.tokenRoot).append(".").append(tokenKey).append(".")
                .append(sessionValue).toString();
    }

    /**
     * 用于应用生成令牌(特殊情况使用)
     *
     * @param tokenKey   {@link MultiForbidRepeatAnno#tokenKey()}
     * @param sessionKey 会话ID的Key：默认是"sessionId"，如果不使用字符值，请传入正确的KEY值
     * @return 令牌值
     */
    public String getObjToken(String tokenKey, String sessionKey, HttpServletRequest request) {
        // 令牌值
        String token = getToken();
        // 会话标识ID
        String sessionValue = FrameUtils.getRequestValue(request, sessionKey);
        // 令牌名称
        String tokenName = getTokenName(tokenKey, sessionValue);
        // 保存令牌并返回
        return cacheService.put(tokenName, token) ? token : null;
    }
}
