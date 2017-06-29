/**
 * 
 */
package com.taotao_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.Msg;
import com.taotao_rest.service.TbitemDescService;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:54:43
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class TbitemDescController {
	@Value("${item_desc}")
	private String key;
	
	@Autowired
	private TbitemDescService itemDescService;
	@RequestMapping("/itemdesc")
	@ResponseBody
	public Msg getItemDescById(long id ){
		key=String.format(key+"%s", id);
		Msg msg = itemDescService.getItendescById(id, key);
		return msg;
	}
}
