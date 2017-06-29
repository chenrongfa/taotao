/**
 * 
 */
package com.taotao.service;

import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月24日下午12:30:09
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class ItemdescServiceImpl implements ItemdescService {

	
	@Override
	public Msg getItemDescById(String url) {
		// TODO Auto-generated method stub
		String string = HttpclientUtils.httpgetForString(url);
		Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
		
		return msg;
	}

}
