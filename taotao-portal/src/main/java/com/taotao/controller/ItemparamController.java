/**
 * 
 */
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月24日下午1:36:11
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class ItemparamController {
	@Value("${taotao_rest_url}")
	private String url;
	@RequestMapping("/item/param/{id}")
	@ResponseBody
	public Msg getItemparam(@PathVariable("id")long id){
		String json = HttpclientUtils.httpgetForString(url+"/itemparam?id="+id);
		Msg msg = JsonUtils.jsonToPojo(json, Msg.class);
		return msg;
	}
}
