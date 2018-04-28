package com.ilearn.dubbo.shop.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.log.LogUtils;
import com.ilearn.dubbo.shop.data.Shop;
import com.ilearn.dubbo.shop.service.ShopService;
import com.ilearn.dubbo.shop.service.ext.ShopExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
public class ShopServiceImpl implements ShopService,ShopExtService {

    private static Logger LOG = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private BaseDao baseDao;

    @Override
    public Shop selectShopById(int shopId) {
        try {
            return baseDao.selectOne(shopId, "ShopMapper.selectShopById");
        } catch (Exception e) {
            LOG.error("ShopMapper.selectShopById error, msg = {}", LogUtils.stackTrace(e));
        }
        return null;
    }
}
