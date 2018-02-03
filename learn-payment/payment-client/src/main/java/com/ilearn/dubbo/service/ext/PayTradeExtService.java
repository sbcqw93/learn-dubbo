package com.ilearn.dubbo.service.ext;

import com.ilearn.dubbo.payment.data.PayTrade;

/**
 *
 * @author George 2018-02-01 下午 08:25:02
 *
 */
public interface PayTradeExtService {
    boolean insertPayTrade(PayTrade payTrade) throws Exception;
}
