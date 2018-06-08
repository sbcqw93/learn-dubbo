package com.ilearn.dubbo.appframework.repeat;

import com.ilearn.dubbo.appframework.cache.CacheService;
import com.ilearn.dubbo.appframework.log.LogUtils;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by LiHuan on 2018/5/21 0021.
 * 通用防步重复请求处理,非注解方式
 */
public class RepeatSubmissionRequest {

    private static Logger LOG = LoggerFactory.getLogger(RepeatSubmissionRequest.class);

    /**
     * 令牌根
     */
    private static String tokenRoot = "tokenRoot.";

    private String tokenPrefix = "csc.";
    // 默认分隔符
    private String delimiter = ".";
    // 分隔符是否需要转义
    private boolean isTurn = true;
    //生成Token时间缓存有效时间, 单位：秒
    private int tokenExpire = 3600;
    //Token验证通过后对其处理方式
    private ProWay processWay = ProWay.clear;
    // 方式为ProWay.waitExpire时, Token有效时间, 单位：秒
    private int effectiveTime = tokenExpire;
    private int randomStrLen = 8;
    private CacheService cacheService;

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setDelimiter(String delimiter, boolean isTurn) {
        this.isTurn = isTurn;
        this.delimiter = delimiter;
    }

    public void setTokenExpire(int tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public void setProcessWay(ProWay processWay) {
        this.processWay = processWay;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public void setRandomStrLen(int randomStrLen) {
        if (randomStrLen > 0)
            this.randomStrLen = randomStrLen;
    }

    public void setting(ProWay processWay, int tokenExpire, int effectiveTime) {
        this.processWay = processWay;
        this.tokenExpire = tokenExpire;
        this.effectiveTime = effectiveTime;
    }

    public RepeatSubmissionRequest() {

    }

    public RepeatSubmissionRequest(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public RepeatSubmissionRequest(CacheService cacheService, ProWay processWay, int tokenExpire, int effectiveTime) {
        this(cacheService);
        this.processWay = processWay;
        this.tokenExpire = tokenExpire;
        this.effectiveTime = effectiveTime;
    }

    public enum ProWay {
        // 验证通过后对Token值的处理方式，目前支持2种，如下
        clear,//直接清除
        waitExpire, //waitExpire：等待过期，依赖setEffectiveTime的值
    }

    public enum SubmissionState {
        empty("1000", "提交请求令牌为空"),
        repeat("1001", "已重复提交"),
        legal("1002", "合法提交请求"),
        unequal("1003", "Token不相等"),
        expire("1004", "Token已过期");

        private String state;
        private String explain;

        SubmissionState(String state, String explain) {
            this.state = state;
            this.explain = explain;
        }

        public String getState() {
            return state;
        }

        public String getExplain() {
            return explain;
        }
    }

    /**
     * 生成Token值, 样例：1526881782628.zf5goveq<br>
     * 合适API方式
     *
     * @return
     */
    public String buildToken(String tokenName) {
        if (null == cacheService) {
            throw new NullPointerException("cacheService对象为空");
        }
        StringBuffer buffer = new StringBuffer(new StringBuffer(String.valueOf(System.currentTimeMillis())));
        buffer.append(delimiter).append(FrameUtils.getRandomStrLen(randomStrLen));

        String tokenValue = buffer.toString();
        String cacheTokenName = this.getCacheTokenName(tokenName);
        cacheService.put(cacheTokenName, tokenValue);
        //cacheService.put(cacheTokenName, tokenValue, tokenExpire);
        return tokenValue;
    }

    /**
     * 生成Token值, 样例：1526881782628.zf5goveq<br>
     * 合适页面方式
     *
     * @return
     */
    public void buildToken(String tokenName, HttpServletRequest request) throws NullPointerException {
        String tokenValue = this.buildToken(tokenName);
        request.setAttribute(tokenName, tokenValue);
    }

    /**
     * 判断Token名是否存在
     * @param tokenName Token名称
     * @return
     */
    public <T> T getTokenValue(String tokenName) {
        String cacheTokenName = this.getCacheTokenName(tokenName);
        return cacheService.get(cacheTokenName);
    }

    /**
     * 判断Token名是否存在
     * @param tokenName Token名称
     * @return
     */
    public boolean isExistToken(String tokenName) {
        String cacheTokenName = this.getCacheTokenName(tokenName);
        return cacheService.isExist(cacheTokenName);
    }

    /**
     * 返回状态值, 说明如下<br>
     * repeatSubmissionState=1000，表示“提交请求令牌为空”<br>
     * repeatSubmissionState=1001，表示“已重复提交”<br>
     * repeatSubmissionState=1002，表示“合法提交请求”<br>
     * repeatSubmissionState=1003，表示“Token不相等”<br>
     * repeatSubmissionState=1004，表示“Token已过期”<br>
     *
     * @param tokenName  Token名称
     * @param tokenValue Token值
     */
    public SubmissionState checkRepeatSubmission(String tokenName, String tokenValue) {
        if (null == cacheService) {
            throw new NullPointerException("cacheService对象为空");
        }
        // 提交请求令牌为空
        if (null == tokenValue || "".equals(tokenValue)) {
            return SubmissionState.empty;
        }
        String cacheTokenName = this.getCacheTokenName(tokenName);
        String cacheTokenValue = cacheService.get(cacheTokenName);
        if (null == cacheTokenValue) {
            LOG.warn("获取缓存中的Token为空, cacheTokenValue={}, tokenExpire={}", cacheTokenValue, tokenExpire);
        }

        String repeatSubmissionState = null;
        String tokenValueLowerCase = tokenValue.toLowerCase();

        // Token处理方式
        // 等待过期
        if (processWay.equals(ProWay.waitExpire)) {
            if (!tokenValueLowerCase.equals(cacheTokenValue)) {
                return SubmissionState.unequal;
            }
            try {
                long buildTokenTimeMillis = 0;
                if (isTurn) {
                    // "."为特殊字符, 直接分隔会报错
                    buildTokenTimeMillis = Long.valueOf(tokenValue.split(new StringBuffer("\\").append(delimiter).toString())[0]);
                } else {
                    buildTokenTimeMillis = Long.valueOf(tokenValue.split(delimiter)[0]);
                }
                long effectiveTimeMillis = (buildTokenTimeMillis + effectiveTime * 1000);
                long currentTimeMillis = System.currentTimeMillis();
                if (effectiveTimeMillis < currentTimeMillis) {
                    cacheService.remove(cacheTokenName);
                    return SubmissionState.expire;
                } else {
                    return SubmissionState.legal;
                }
            } catch (NumberFormatException e) {
                cacheService.remove(cacheTokenName);
                LOG.error("Token中毫秒转换失败, tokenValue={}, error={}", tokenValue, LogUtils.stackTrace(e));
                return SubmissionState.expire;
            }
        }
        // 直接清除
        else {
            cacheService.remove(cacheTokenName);
            // 合法请求
            if (tokenValueLowerCase.equals(cacheTokenValue)) {
                return SubmissionState.legal;
            }
            // 已重复提交
            else if (null != tokenValueLowerCase && null == cacheTokenValue) {
                return SubmissionState.repeat;
            }
            // Token不相等
            else {
                return SubmissionState.unequal;
            }
        }
    }

    private String getCacheTokenName(String tokenName) {
        return new StringBuffer(tokenRoot).append(tokenPrefix).append(tokenName).toString();
    }

}