/**
 * 
 */
package com.taotao_rest.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tb_order")
public class UserOrders implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
@TableId(type=IdType.INPUT,value="order_id")
	private String orderId;

    private String payment;
@TableField("payment_type")
    private Integer paymentType;
@TableField("post_fee")
    private String postFee;
    private Integer status;
@TableField("create_time")
    private Date createTime;

@TableField("shipping_name")
    private String shippingName;
@TableField("shipping_code")
    private String shippingCode;
@TableField("user_id")
    private Long userId;
@TableField("buyer_message")
    private String buyerMessage;
@TableField("buyer_nick")
    private String buyerNick;
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getPayment() {
	return payment;
}
public void setPayment(String payment) {
	this.payment = payment;
}
public Integer getPaymentType() {
	return paymentType;
}
public void setPaymentType(Integer paymentType) {
	this.paymentType = paymentType;
}
public String getPostFee() {
	return postFee;
}
public void setPostFee(String postFee) {
	this.postFee = postFee;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getShippingName() {
	return shippingName;
}
public void setShippingName(String shippingName) {
	this.shippingName = shippingName;
}
public String getShippingCode() {
	return shippingCode;
}
public void setShippingCode(String shippingCode) {
	this.shippingCode = shippingCode;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getBuyerMessage() {
	return buyerMessage;
}
public void setBuyerMessage(String buyerMessage) {
	this.buyerMessage = buyerMessage;
}
public String getBuyerNick() {
	return buyerNick;
}
public void setBuyerNick(String buyerNick) {
	this.buyerNick = buyerNick;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "UserOrders [orderId=" + orderId + ", payment=" + payment + ", paymentType=" + paymentType + ", postFee="
			+ postFee + ", status=" + status + ", createTime=" + createTime + ", shippingName=" + shippingName
			+ ", shippingCode=" + shippingCode + ", userId=" + userId + ", buyerMessage=" + buyerMessage
			+ ", buyerNick=" + buyerNick + "]";
}


    
}
