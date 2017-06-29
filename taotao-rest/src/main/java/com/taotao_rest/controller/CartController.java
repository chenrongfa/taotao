/**
 * 
 */
package com.taotao_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao_rest.bean.Cart;
import com.taotao_rest.service.CartService;

/**
 * @description :TODO
 * @since 2017年6月26日下午3:21:17
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@RequestMapping("/add/shopcar")
	@ResponseBody
	public Msg addShopCart(Cart cart){
		System.out.println("taotao-rest"+cart.toString());
		Msg msg = cartService.addShopcar(cart);
		return msg;
	}
	@RequestMapping("/show/shopcar")
	@ResponseBody
	public Msg showShopCart(){
		System.out.println("show");
		Msg msg = cartService.getCartResultById(Cart.class);
		return msg;
	}
	@RequestMapping("/update/cart/num")
	@ResponseBody
	public Msg updateNumByItemid(long id,long num){
		System.out.println("show");
		Msg msg = cartService.updataNumByItemid(id, num);
		return msg;
	}
	@RequestMapping("/delete/shopcar")
	@ResponseBody
	public Msg deleteCartById(long id){
		System.out.println("show");
		Msg msg = cartService.deleteShopcar(id);
		return msg;
	}
}
