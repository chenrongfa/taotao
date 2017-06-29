/**
 * 
 */
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Cart;
import com.taotao.bean.CartResult;
import com.taotao.bean.Msg;
import com.taotao.service.CartService;
import com.taotao.service.CartServiceImpl;

/**
 * @description :TODO
 * @since 2017年6月26日下午3:27:00
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class ShopCartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/add/shopCar")
	@ResponseBody
	public Msg addShopCar(Cart cart) {
		System.out.println(cart.toString());
		Msg msg = cartService.addShopcar(cart);
		System.out.println("msg" + msg.toString());
		return msg;
	}

	@RequestMapping("/item/cart")

	public String goShopcart(Model model) {
		CartResult cartResult = cartService.getCartResultById();
		if(cartResult!=null){
	     model.addAttribute("totalPrice", cartResult.getTotal());
	     model.addAttribute("cartList", cartResult.getCarts());
		}
		return "cart";
	}
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public Msg updateTotal(@PathVariable("itemId")long itemId,@PathVariable("num")long num){
		System.out.println("itemid"+itemId+"num"+num);
	    Msg msg=cartService.updataNumByItemid(itemId,num);
		
		return msg;
	}
	@RequestMapping("/cart/delete/{itemId}")
	
	public String deleteCartById(@PathVariable("itemId")long itemId){
		System.out.println("itemid"+itemId);
		Msg msg=cartService.deleteShopcar(itemId);
		
		return "redirect:/item/cart.html";
	}
	
}
