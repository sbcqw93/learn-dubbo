package com.ilearn.dubbo.order.data;

import java.io.Serializable;


/**
 * 
 * @author George 2018-02-01 下午 08:25:29
 * 
 */
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** 产品ID */
	private int productId;
	/** 卖家ID */
	private int sellerId;
	/** 买家ID */
	private int buyerId;
	/** 交易状态：10成功,20退款,30失败 */
	private int orderStatus;

	public Order() {
		super();
	}

	public Order(int id, int productId, int sellerId, int buyerId, int orderStatus) {
		this.id = id;
		this.productId = productId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.orderStatus = orderStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id = " + id + ", productId = " + productId + ", sellerId = " + sellerId + ", buyerId = " + buyerId + ", orderStatus = " + orderStatus + "]";
	}

}
