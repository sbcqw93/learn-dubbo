package com.ilearn.dubbo.payment.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.log.LogUtils;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.payment.data.PayFund;
import com.ilearn.dubbo.payment.service.PayFundService;
import com.ilearn.dubbo.service.ext.PayFundExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author George 2018-02-01 下午 08:25:02
 */
public class PayFundServiceImpl implements PayFundService, PayFundExtService {

    @Autowired
    private BaseDao baseDao;

    private Integer result = 0;
    private static Logger LOG = LoggerFactory.getLogger(PayFundServiceImpl.class);

    @Override
    public PayFund selectPayFundById(int memberId) {
        try {
            return baseDao.selectOne(memberId, "PayFundMapper.selectPayFundById");
        } catch (Exception e) {
            LOG.error("selectPayFundById error, msg = {}", LogUtils.stackTrace(e));
        }
        return null;
    }

    @Override
    public boolean updatePayFundBalanceById(int buyerId, int sellerId, String balance) throws Exception {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("buyerId", buyerId);
            params.put("sellerId", sellerId);
            params.put("balance", balance);
            result = baseDao.update(params, "PayFundMapper.updatePayFundBalanceById");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }
}
