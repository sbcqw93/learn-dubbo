package com.ilearn.dubbo.payment.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.payment.data.PayTrade;
import com.ilearn.dubbo.payment.service.PayTradeService;
import com.ilearn.dubbo.service.ext.PayTradeExtService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author George 2018-02-01 下午 08:25:02
 */
public class PayTradeServiceImpl implements PayTradeService, PayTradeExtService {

    @Autowired
    private BaseDao baseDao;

    private Integer result = 0;

    public boolean insertPayTrade(PayTrade payTrade) throws Exception {
        try {
            result = baseDao.insert(payTrade, "OrderMapper.insertPayTrade");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }
}
