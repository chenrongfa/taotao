/**
 * 
 */
package com.taotao_rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao.utils.JsonUtils;
import com.taotao_rest.bean.Cart;
import com.taotao_rest.bean.CartResult;

/**
 * @description :购物车的crud
 * @since 2017年6月26日下午2:20:56
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class CartServiceImpl implements CartService {
	@Value("${cart_hash_prix}")
	private String cart_hash_prix;
	@Value("${cart_key}")
	private String cart_key;

	@Autowired
	private RedisService redisService;

	@Override
	public Msg addShopcar(Cart cart) {

		Msg msg = new Msg();
		System.out.println(cart_hash_prix);
		Map<Object, Object> hashMap = redisService.getHashMap(cart.getClass());
		if(hashMap.containsKey((cart_hash_prix + cart.getId()))){
			//之前就有的加yi
			System.out.println("相等");
			Cart object = (Cart) hashMap.get(cart_hash_prix + cart.getId());
			object.setNum(object.getNum()+1);
			
		}else{
		hashMap.put(cart_hash_prix + cart.getId(), cart);
		System.out.println(1);
		System.out.println("不相等呢");
		}
		boolean success = redisService.putHashMap(hashMap, cart.getClass());
		if (success) {
			System.out.println("已经插入");
			msg.addSuccessMessage();
		} else {
			System.out.println("插入错误");
			msg.addErrorMessage();
		}

		return msg;
	}

	@Override
	public Msg updateShopcar(Cart cart) {
		Msg msg = new Msg();
		System.out.println(cart_hash_prix);
		Map<Object, Object> hashMap = redisService.getHashMap(cart.getClass());

		hashMap.put(cart_hash_prix + cart.getId(), cart);
		System.out.println(1);
		boolean success = redisService.putHashMap(hashMap, cart.getClass());
		if (success) {
			System.out.println("已经更新");
			msg.addSuccessMessage();
		} else {
			System.out.println("更新错误");
			msg.addErrorMessage();
		}

		return msg;
	}

	@Override
	public Msg deleteShopcar(long itemId) {
		Msg msg = new Msg();
		boolean success = redisService.delHashValue(cart_key, cart_hash_prix + itemId);
		if (success)
			msg.addSuccessMessage();
		else
			msg.addErrorMessage();
		return msg;
	}

	@Override
	public Msg getCartResultById(Class<?> cls) {
		Msg msg = new Msg();

		System.out.println(cart_hash_prix);
		CartResult cartResult = new CartResult();
		Map<Object, Object> hashMap = redisService.getHashMap(cls);
		long total = 0;
		List<Cart> carts = new ArrayList<>();
		if (hashMap.size() > 0) {
			Iterator<Entry<Object, Object>> iterator = hashMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Object, Object> next = iterator.next();
				Cart cart = (Cart) next.getValue();
				total += cart.getPrice() * cart.getNum();
				carts.add(cart);
			}

			cartResult.setCarts(carts);
			cartResult.setTotal(total);
			msg.getDataBean().put("shop", JsonUtils.objectToJson(cartResult));
			msg.addSuccessMessage();
			return msg;
		} else {
			return msg.addErrorMessage();
		}

	}

	@Override
	public Msg updataNumByItemid(long itemId, long num) {

		Msg msg = new Msg();
		System.out.println(cart_hash_prix);
		Map<Object, Object> hashMap = redisService.getHashMap(Cart.class);

		Cart cart = (Cart) hashMap.get(cart_hash_prix + itemId);
		if (cart != null) {
			cart.setNum((int) num);
			boolean success = redisService.putHashMap(hashMap, cart.getClass());
			if (success) {
				System.out.println("已经插入");
				return msg.addSuccessMessage();
			} else {
				System.out.println("插入错误");
				msg.addErrorMessage();
			}
		}

		return msg;
	}

}
