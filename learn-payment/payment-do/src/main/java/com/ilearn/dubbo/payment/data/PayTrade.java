package com.ilearn.dubbo.payment.data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
 * @author George 2018-02-01 ���� 08:25:02
 *
 */
public class PayTrade implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** ����ID */
	private int orderId;
	/** ���׽�� */
	private BigDecimal tradeAmount;
	/** ����״̬��10�ɹ�,20�˿�,30ʧ�� */
	private int tradeStatus;

	public PayTrade() {
		super();
	}

	public PayTrade(int id, int orderId, BigDecimal tradeAmount, int tradeStatus) {
		this.id = id;
		this.orderId = orderId;
		this.tradeAmount = tradeAmount;
		this.tradeStatus = tradeStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	@Override
	public String toString() {
		return "PayTrade [id = " + id + ", orderId = " + orderId + ", tradeAmount = " + tradeAmount + ", tradeStatus = " + tradeStatus + "]";
	}

}
