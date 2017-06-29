/**
 * 
 */
package com.taotao.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.bean.Cart;
import com.taotao.bean.CartResult;
import com.taotao.bean.Msg;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月26日下午3:29:34
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class CartServiceImpl implements CartService {

	@Value("${taotao_rest_url}")
	private String taotao_rest_url;
	@Override
	public Msg addShopcar(Cart cart) {
		Msg msg=null;
		Map<String, String> param=new HashMap<>();
		param.put("id", cart.getId()+"");
		param.put("image", cart.getImage());
		param.put("title", cart.getTitle());
		param.put("num", cart.getNum()+"");
		param.put("price", cart.getPrice()+"");
		
		String string = HttpclientUtils.httppost(taotao_rest_url+"/add/shopcar", param);
		try {
			
			msg = JsonUtils.jsonToPojo(string, Msg.class);
			msg.addSuccessMessage();
			return msg;
		} catch (Exception e) {
			msg.addErrorMessage(100, e.getMessage());
		}
		
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.CartService#updateShopcar(com.taotao.bean.Cart)
	 */
	@Override
	public Msg updateShopcar(Cart object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.CartService#deleteShopcar(long)
	 */
	@Override
	public Msg deleteShopcar(long itemId) {
		String string = HttpclientUtils.httpgetForString(taotao_rest_url+"/delete/shopcar?id="+itemId);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.CartService#getCartResultById(long, java.lang.Class)
	 */
	@Override
	public CartResult getCartResultById() {
		String string = HttpclientUtils.httpgetForString(taotao_rest_url+"/show/shopcar");
		Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
		if(msg.getStatus()==200){
		return	 JsonUtils.jsonToPojo((String)msg.getDataBean().get("shop"), CartResult.class);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.CartService#updataNumByItemid(long, long)
	 */
	@Override
	public Msg updataNumByItemid(long itemId, long num) {
		String string = HttpclientUtils.httpgetForString(taotao_rest_url+"/update/cart/num?id="+itemId+"&num="+num);
		
		try {
			Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			return new Msg().addErrorMessage();
		}
		
	}

}
