package com.ilearn.dubbo.shop.data;

import java.io.Serializable;


/**
 * 
 * @author George 2018-02-01 下午 08:25:49
 * 
 */
public class Shop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** 会员ID */
	private int memberId;
	/** 旺铺名称 */
	private String shopName;

	public Shop() {
		super();
	}

	public Shop(int id, int memberId, String shopName) {
		this.id = id;
		this.memberId = memberId;
		this.shopName = shopName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "Shop [id = " + id + ", memberId = " + memberId + ", shopName = " + shopName + "]";
	}

}
