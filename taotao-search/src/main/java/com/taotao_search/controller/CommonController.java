/**
 * 
 */
package com.taotao_search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao_search.bean.Msg;
import com.taotao_search.service.ItemSearchService;

/**
 * @description :TODO
 * @since 2017年6月23日上午9:59:15
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class CommonController {

	
	@Autowired
	private ItemSearchService itemSearchService;
	 @RequestMapping("/import")
	 @ResponseBody
		public Msg dataImport(){
			Msg msg = itemSearchService.dataImport();
			return msg;
		}
}
