/**
 * 
 */
package com.taotao_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao_rest.service.TbItemService;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:50:12
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class TbItemController {
	@Autowired
	private TbItemService itemService;
	@RequestMapping("/item")
	@ResponseBody
	public Msg getItemById(long id){
		System.out.println("item");
		Msg msg = itemService.getItemById(id);
		return  msg;
	}
}
