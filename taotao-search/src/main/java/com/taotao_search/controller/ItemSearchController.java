/**
 * 
 */
package com.taotao_search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao_search.bean.Msg;
import com.taotao_search.service.ItemSearchService;

/**
 * @description :TODO
 * @since 2017年6月23日上午10:59:34
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class ItemSearchController {
 
	@Autowired
	private ItemSearchService itemSearchService;
	@SuppressWarnings("unused")
	@RequestMapping("/search")
	@ResponseBody()
	public Msg search(String q,@RequestParam(value="page",defaultValue="1") long page,
			@RequestParam(value="rows",defaultValue="30")long rows){
		Msg msg=new Msg();
		try {
			q=new String(q.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(q);
		if(q==null){
			return msg.addErrorMessage(100, "q的参数不能为空");
		}	
		Msg search = itemSearchService.search(q, page, rows,"item_title");
		
		return search;
	}
}
