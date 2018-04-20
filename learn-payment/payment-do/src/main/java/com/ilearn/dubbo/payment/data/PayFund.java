package com.ilearn.dubbo.payment.data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * @author George 2018-02-01 下午 08:25:02
 * 
 */
public class PayFund implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** 会员ID */
	private int memberId;
	/** 手机号 */
	private BigDecimal balance;

	public PayFund() {
		super();
	}

	public PayFund(int id, int memberId, BigDecimal balance) {
		this.id = id;
		this.memberId = memberId;
		this.balance = balance;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "PayFund [id = " + id + ", memberId = " + memberId + ", balance = " + balance + "]";
	}

}
