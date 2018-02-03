package com.ilearn.dubbo.payment.service;

import com.ilearn.dubbo.payment.data.PayTrade;

/**
 *
 * @author George 2018-02-01 下午 08:25:02
 *
 */
public interface PayTradeService {
    boolean insertPayTrade(PayTrade payTrade) throws Exception;
}
