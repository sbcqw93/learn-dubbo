package com.ilearn.dubbo.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.order.data.Order;
import com.ilearn.dubbo.service.OrderService;
import com.ilearn.dubbo.service.ext.OrderExtService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2018/2/3 0003.
 */
public class OrderServiceImpl implements OrderService, OrderExtService {

    @Autowired
    private BaseDao baseDao;

    private Integer result = 0;

    @Override
    public boolean insertOrder(Order order) throws Exception {
        try {
            result = baseDao.insert(order, "OrderMapper.insertOerder");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }

    @Override
    public boolean updateOrderStatus(int orderId, int sellerId, int buyerId) throws Exception {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orderId", orderId);
            params.put("sellerId", sellerId);
            params.put("buyerId", buyerId);
            result = baseDao.update(params, "MemberMapper.updateMemberRank");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }
}
