package com.ilearn.dubbo.member.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.member.service.ext.MemberExtService;
import com.ilearn.dubbo.member.service.MemberService;
import com.ilearn.dubbo.order.data.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2018/2/2 0002.
 */
public class MemberServiceImpl implements MemberService, MemberExtService {


    public final static Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private BaseDao baseShopDao;
    @Autowired
    private BaseDao baseOrderDao;

    private Integer result = 0;

    @Override
    public boolean updateMemberRank(int memberId, float rank) throws Exception {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("memberId", memberId);
            params.put("rank", rank);
            result = baseDao.update(params, "MemberMapper.updateMemberRank");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }

    @Override
    public boolean createOrder(int shopId, int sellerId, int buyerId, int productId, int quantity) throws Exception {
        // 根据productId,查询商品单价
        BigDecimal price = new BigDecimal(1000);
        price = price.multiply(new BigDecimal(quantity));
        int orderStatus = 40;
        Order order = new Order(productId, sellerId, buyerId, orderStatus, price);
        // 创建订单
        result = baseOrderDao.insert(order, "OrderMapper.insertOerder");
        LOG.info("创建订单result = {}", result);

        //制造异常
        Integer.parseInt("a");

        //扣库存
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("shopId", shopId);
        params.put("productId", productId);
        params.put("sellCount", quantity);
        baseShopDao.update(params, "ProductMapper.updateProductStock");
        LOG.info("扣库存result = {}", result);
        return false;
    }
}
