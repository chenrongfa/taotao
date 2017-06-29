/**
 * 
 */
package com.taotao_rest.bean;

import java.util.Date;

/**
 * @description :修改订单状态
 * @since 2017年6月27日下午12:45:28
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 *  "orderId": "100544",//订单编号
    "status": 2, //订单状态
    "paymentTime": "1414489420444" //付款时间
 */
public class OrderStatus {
	private String orderId;
	private int status;
	private Date paymentTime;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	@Override
	public String toString() {
		return "OrderStatus [orderId=" + orderId + ", status=" + status + ", paymentTime=" + paymentTime + "]";
	}

}
