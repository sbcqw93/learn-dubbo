package com.ilearn.dubbo.payment.service;

import com.ilearn.dubbo.payment.data.PayFund;

/**
 * @author George 2018-02-01 下午 08:25:02
 */
public interface PayFundService {
    public PayFund selectPayFundById(int memberId);

    public boolean updatePayFundBalanceById(int buyerId, int sellerId, String balance) throws Exception;
}
