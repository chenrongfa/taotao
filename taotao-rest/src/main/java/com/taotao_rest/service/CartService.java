/**
 * 
 */
package com.taotao_rest.service;

import org.springframework.beans.factory.annotation.Value;

import com.taotao.bean.Msg;
import com.taotao_rest.bean.Cart;

/**
 * @description :TODO
 * @since 2017年6月26日下午12:37:40
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface CartService {
	

	Msg addShopcar(Cart object);
	Msg updateShopcar(Cart object);
	Msg deleteShopcar(long itemId);
	Msg getCartResultById(Class<?> cls);
	Msg updataNumByItemid(long itemId, long num);
}
