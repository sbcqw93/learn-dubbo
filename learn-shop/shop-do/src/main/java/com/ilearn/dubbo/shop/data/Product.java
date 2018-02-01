package com.ilearn.dubbo.shop.data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * @author George 2018-02-01 下午 08:25:49
 * 
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** 旺铺ID */
	private int shopId;
	/** 产品名称 */
	private String productName;
	/** 价格 */
	private BigDecimal price;
	/** 库存 */
	private int stock;

	public Product() {
		super();
	}

	public Product(int id, int shopId, String productName, BigDecimal price, int stock) {
		this.id = id;
		this.shopId = shopId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id = " + id + ", shopId = " + shopId + ", productName = " + productName + ", price = " + price + ", stock = " + stock + "]";
	}

}
