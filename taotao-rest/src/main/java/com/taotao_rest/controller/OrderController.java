/**
 * 
 */
package com.taotao_rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.taotao.bean.Msg;
import com.taotao.utils.JsonUtils;
import com.taotao_rest.bean.OrderCreate;
import com.taotao_rest.bean.OrderStatus;
import com.taotao_rest.service.OrderService;

/**
 * @description :TODO
 * @since 2017年6月26日下午8:25:16
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/create/order")
	@ResponseBody
	public Msg createOrder(@RequestBody String json, HttpServletRequest request,
			@CookieValue(value = "TT_TOKEN", defaultValue = "dd") String cook) {
		System.out.println("进来" + cook);
		Msg msg = orderService.createOrder(JsonUtils.jsonToPojo(json, OrderCreate.class), request);
		return msg;
	}



	@RequestMapping("/order/changeStatus")
	@ResponseBody
	public Msg changeOrderStatus( OrderStatus status 
			) {
		Msg msg = orderService.updateOrderStatus(status);
		return msg;
	}
	@RequestMapping("/order/list/{userID}/{page}/{count}")
	@ResponseBody
	public Msg getOrderList(@PathVariable("userID") long userId,@PathVariable(value="page",required=false) long page,
			@PathVariable("page") long count
			) {
		System.out.println(123);
	     Msg msg= orderService.getUserOrderList(userId,page,count);
		return msg;
	}
	@RequestMapping("/order/info/{orderId}")
	@ResponseBody
	public Msg getOrderCreateByOderId(@PathVariable("orderId") long userId
			) {
		
		Msg msg= orderService.getOrderCreateByOderId(userId+"");
		return msg;
	}
}
