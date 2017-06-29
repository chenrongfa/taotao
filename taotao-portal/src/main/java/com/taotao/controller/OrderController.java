/**
 * 
 */
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.bean.CartResult;
import com.taotao.bean.Msg;
import com.taotao.bean.OrderCreate;
import com.taotao.bean.TbOrder;
import com.taotao.bean.TbOrderItem;
import com.taotao.bean.TbOrderShipping;
import com.taotao.service.CartService;
import com.taotao.service.OrderService;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月26日下午6:56:50
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class OrderController {
	@Autowired
	private CartService cartService;

	@Autowired

	private OrderService orderService;

	@RequestMapping("/order/order-cart")
	public String goOrder_cart(Model model) {
		CartResult cartResult = cartService.getCartResultById();
		if (cartResult != null) {
			model.addAttribute("cartList", cartResult.getCarts());
		}

		return "order-cart";
	}

	@RequestMapping("/order/create")
	public String CreateOrder(OrderCreate order, @CookieValue(value = "TT_TOKEN", defaultValue = "dd") String cook) {
		Msg msg = orderService.createOrder(JsonUtils.objectToJson(order),"TT_TOKEN",cook);
		System.out.println("cook"+cook);
		System.out.println("create" + msg.toString());
		return "redirect:/my/orders.html";
	}
	@RequestMapping("/my/orders")
	public String goMyOrder(Model model) {
		System.out.println("my_orders");
	
		return "my-orders";
	}
	

}
