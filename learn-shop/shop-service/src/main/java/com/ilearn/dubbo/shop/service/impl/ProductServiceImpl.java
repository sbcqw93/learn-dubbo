package com.ilearn.dubbo.shop.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.log.LogUtils;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.shop.data.Product;
import com.ilearn.dubbo.shop.service.ProductService;
import com.ilearn.dubbo.shop.service.ext.ProductExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2018/2/3 0003.
 */
public class ProductServiceImpl implements ProductService, ProductExtService {

    private static Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private BaseDao baseDao;

    private Integer result = 0;

    @Override
    public Product selectProductById(int productId) {
        try {
            return baseDao.selectOne(productId, "ProductMapper.selectProductById");
        } catch (Exception e) {
            LOG.error("ProductMapper.selectProductById error, msg = {}", LogUtils.stackTrace(e));
        }
        return null;
    }

    @Override
    public boolean updateProductStock(int productId, int shopId, int sellCount) throws Exception {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("productId", productId);
            params.put("shopId", shopId);
            params.put("sellCount", sellCount);
            result = baseDao.update(params, "ProductMapper.updateProductStock");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }
}
