/**
 * 
 */
package com.taotao_rest.service;

import javax.servlet.http.HttpServletRequest;

import com.taotao.bean.Msg;
import com.taotao_rest.bean.OrderCreate;
import com.taotao_rest.bean.OrderStatus;

/**
 * @description :TODO
 * @since 2017年6月26日下午8:16:25
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface OrderService {
	Msg createOrder(OrderCreate order, HttpServletRequest request);

	/**
	 * @param status
	 */
	Msg updateOrderStatus(OrderStatus status);

	/**
	 * 通过userid分页查询
	 * @param userId
	 * @param page
	 * @param count
	 * @return
	 */
	Msg getUserOrderList(long userId, long page, long count);

	/**
	 * @param userId
	 * @return
	 */
	Msg getOrderCreateByOderId(String userId);
}
