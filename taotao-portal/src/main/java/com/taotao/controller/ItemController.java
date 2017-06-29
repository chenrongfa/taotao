/**
 * 
 */
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao.service.TbItemService;

/**
 * @description :TODO
 * @since 2017年6月23日下午6:36:12
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class ItemController {
	@Autowired
	private TbItemService tbItemService;
	
	@Value("${taotao_rest_url}")
 private String url;
	@RequestMapping("/item/{id}")
	public String resolveItem(@PathVariable("id")long id,Model model){
		Msg msg=new Msg();
		model.addAttribute("id", id);
		System.out.println(url);
		TbItem item = tbItemService.getItemById(url+"/item?id="+id);
		if(item!=null)
		model.addAttribute("item", item);
		System.out.println(id);
		return "item";
	}
}
