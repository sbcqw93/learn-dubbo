package com.ilearn.dubbo.service.ext;

import com.ilearn.dubbo.order.data.Order;

/**
 * Created by George on 2018/2/3 0003.
 */
public interface OrderExtService {
    public boolean insertOrder(Order order) throws Exception;

    public boolean updateOrderStatus(int orderId, int sellerId, int buyerId) throws Exception;
}
