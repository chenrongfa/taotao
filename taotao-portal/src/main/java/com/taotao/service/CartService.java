/**
 * 
 */
package com.taotao.service;

import com.taotao.bean.Cart;
import com.taotao.bean.CartResult;
import com.taotao.bean.Msg;


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
	CartResult getCartResultById();
	/**
	 * 通过id 更新数量
	 * @param itemId
	 * @param num
	 * @return
	 */
	Msg updataNumByItemid(long itemId, long num);
}
