/**
 * 
 */
package com.taotao_rest.bean;

import java.util.List;

import com.taotao.bean.TbOrder;
import com.taotao.bean.TbOrderItem;
import com.taotao.bean.TbOrderShipping;

/**
 * @description :创建订单
 * @since 2017年6月26日下午7:47:50
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class OrderCreate extends TbOrder {
	
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	@Override
	public String toString() {
		return "OrderCreate [orderItems=" + orderItems + ", orderShipping=" + orderShipping + "]";
	}
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
