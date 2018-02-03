package com.ilearn.dubbo.shop.service.ext;

import com.ilearn.dubbo.shop.data.Product;

/**
 * Created by George on 2018/2/3 0003.
 */
public interface ProductExtService {
    public Product selectProductById(int productId);

    public boolean updateProductStock(int productId, int shopId, int sellCount) throws Exception;
}
