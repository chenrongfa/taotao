/**
 * 
 */
package com.taotao.service;

import javax.servlet.http.HttpUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月26日下午8:18:18
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${taotao_rest_url}")
	private String url;
	@Override
	public Msg createOrder(String json) {
		String string = HttpclientUtils.httpPostWithJson(url+"/create/order", json);
		try {
			Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
			return msg;
		} catch (Exception e) {
		return new Msg().addErrorMessage();
		}
	}
	
	@Override
	public Msg createOrder(String json, String cookKey, String cookValue) {
		String string = HttpclientUtils.httpPostWithJsonAndCookie(url+"/create/order", json,
				cookKey,cookValue);
		try {
			Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
			return msg;
		} catch (Exception e) {
		return new Msg().addErrorMessage();
		}
	}

}
