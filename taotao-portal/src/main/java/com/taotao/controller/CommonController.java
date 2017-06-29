/**
 * 
 */
package com.taotao.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.BIgAdvertiment;
import com.taotao.bean.Cart;
import com.taotao.bean.ItemSearchResult;
import com.taotao.bean.Msg;
import com.taotao.bean.TbContent;
import com.taotao.service.BIgAdvertimentService;
import com.taotao.service.ItemSearchService;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * description :TODO createTime:2017年6月15日 下午3:03:29 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Controller
public class CommonController {
	@Autowired
    private  BIgAdvertimentService adService;
	@Autowired
	
	private ItemSearchService itemservice;
	@RequestMapping("/index")
	public String goIndex(Map<String, Object> map) {
		System.out.println("444");
		List<BIgAdvertiment> advertimentByHttpcliean = adService.getBIgAdvertimentByHttpcliean("http://localhost:8081/taotao-rest/tbcontent/89");
		map.put("ads", JsonUtils.objectToJson(advertimentByHttpcliean));
		return "index";
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public List<TbContent> test() {
		String string = HttpclientUtils.httpgetForString("http://localhost:8081/taotao-rest/tbcontent/89");

		List<TbContent> jsonToList = JsonUtils.jsonToList(string, TbContent.class);
		return jsonToList;
	}
	
	@RequestMapping(value = "/search")
	public String goPages( String q,@RequestParam(value="page",defaultValue="1") long page,Model model) {
		System.out.println(123);
		try {
			q=new String(q.getBytes("iso8859-1"),"utf-8");
			model.addAttribute("query", q);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(q);
		ItemSearchResult itemInfo = itemservice.getItemInfo(q, page);
		
		if(itemInfo!=null){
			System.out.println(itemInfo.toString());
			model.addAttribute("itemList", itemInfo.getItem());
			model.addAttribute("page", itemInfo.getCurrentPage());
			model.addAttribute("pages", itemInfo.getPageCount());
		}
		return "search";
	}
	@RequestMapping("/cart/add/{id}")
	public String addCart(@PathVariable("id") long id){
		return "cart";
	}
	
}
