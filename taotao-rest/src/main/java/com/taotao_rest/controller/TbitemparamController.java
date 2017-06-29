/**
 * 
 */
package com.taotao_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao_rest.service.TbitemparamService;

/**
 * @description :TODO
 * @since 2017年6月24日上午10:16:15
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class TbitemparamController {
	@Autowired
	private TbitemparamService tbitemparamService;
	
	@Value("${item_param}")
	private String key;
	@RequestMapping("/itemparam")
	@ResponseBody
	public Msg getItemparamByID(long id){
	key=String.format(key+"%s", id);
		Msg msg = tbitemparamService.getParamByItemId(id, key);
		
		return msg;
	}
}
