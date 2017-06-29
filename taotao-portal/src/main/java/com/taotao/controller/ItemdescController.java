/**
 * 
 */
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao.service.ItemdescService;

/**
 * @description :TODO
 * @since 2017年6月24日下午12:22:41
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class ItemdescController {
	@Value("${taotao_rest_url}")
	private String url;
	@Autowired
	private ItemdescService itemdescService;

	@RequestMapping("/item/desc/{id}")
	@ResponseBody
	public Msg getItemDesc(@PathVariable("id") long id, String key) {
		System.out.println("4444");
	 Msg msg = itemdescService.getItemDescById(url+"/itemdesc?id="+id);
		return msg;
	}
}
