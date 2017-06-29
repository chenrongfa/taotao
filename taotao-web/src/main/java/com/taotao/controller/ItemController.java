package com.taotao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao.service.impl.TbitemServiceimpl;

@Controller
public class ItemController {
	@Autowired
   private TbitemServiceimpl itemService;
	/**
	 *  test
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/item/{id}")
	@ResponseBody
	public TbItem findItemById(@PathVariable(value="id") int id
			,HttpServletRequest request){
		System.out.println("dsd");
		System.out.println(request.getServletPath());
		System.out.println(request.getRealPath("WEB-INF"));
		System.out.println(request.getServletContext().getContextPath());
		return itemService.selectById(id);
	}
	/**
	 *  分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/item/list")
	public EasyUIData getEasyUIDataByItem(@RequestParam(value="page",required=false,defaultValue="1")
	int page,@RequestParam(value="rows",required=false,defaultValue="10")int rows){
		System.out.println("jinlai?");
		EasyUIData easyUIData = itemService.getEasyUIDataByItem(page, rows);
		return easyUIData;
	}
	/**
	 *  通过id删除 item
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	public Msg deleteByIds(@RequestParam(value="ids",required=false,defaultValue="1")
	long ids){
		
		Msg msg=new Msg();
		boolean deleteByIds = itemService.deleteByIds(ids);
		if(deleteByIds){
			msg.addSuccessMessage();
			
		}else{
			msg.addErrorMessage();
		}
		return msg;
	}
	/**
	 * 商品 保存
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public Msg ItemSave(TbItem item,@RequestParam("desc") String desc,@RequestParam("itemParams")
	String param) {
		System.out.println(3);
		Msg msg = new Msg();
		boolean save;
		try {
			save = itemService.save(item,desc,param);
			if(save)
			msg.addSuccessMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.addErrorMessage();
		}
		
			
		
			
		
		return msg;
	}
	/**
	 *  通过id  下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/instock")
	@ResponseBody
	public Msg itemUnderCarriage(@RequestParam("ids") String ids){
		System.out.println(1);
		Msg msg=new Msg();
		if(ids==null){
			msg.addErrorMessage();
		}
		String []id=ids.split(",");
		try {
			itemService.updateStatusByIds(id,(byte)2);
			msg.addSuccessMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.addErrorMessage();
		}
		return msg;
	}
	/**
	 *  通过id  上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/reshelf")
	@ResponseBody
	public Msg reshelf(@RequestParam("ids") String ids){
		System.out.println(1);
		Msg msg=new Msg();
		if(ids==null){
			msg.addErrorMessage();
		}
		String []id=ids.split(",");
		try {
			itemService.updateStatusByIds(id,(byte)1);
			msg.addSuccessMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.addErrorMessage();
		}
		return msg;
	}
}
