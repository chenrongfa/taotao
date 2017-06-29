/**
 * 
 */
package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.Msg;
import com.taotao.bean.TbContentCategory;
import com.taotao.service.dao.TbContentCategoryService;

/**
 * description :TODO createTime:2017年6月16日 上午11:01:32 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Controller
public class TbContentcategoryController {
	@Autowired
	private TbContentCategoryService contentService;

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyuiTreeNode> getContentCategoryList(
			@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
		System.out.println("22");
		List<EasyuiTreeNode> easyuiData = contentService.selectListEasyuiTreeByParentId(id);

		return easyuiData;
	}

	@RequestMapping("/content/category/create")
	@ResponseBody
	public Msg createContentCategory(long parentId, String name) {
		System.out.println("33");
		Msg msg=new Msg();
		long id = contentService.insertByCreateContentcategory(name, parentId);
		if(id<0){
		return	msg.addErrorMessage();
		}
		msg.addSuccessMessage();
		msg.getDataBean().put("id", id);
		return msg;
	}
	@RequestMapping("/content/category/update")
	@ResponseBody
	public Msg updateNameByid(long id, String name) {
		System.out.println("33");
		Msg msg=new Msg();
		 boolean success = contentService.updateNameByid(name, id);
		if(!success){
			return	msg.addErrorMessage();
		}
		msg.addSuccessMessage();
		
		return msg;
	}
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public Msg deleteContentCategoryById(long id) {
		System.out.println("331");
		Msg msg=new Msg();
		boolean success = contentService.deleteContentCategoryById(id);
		if(!success){
			return	msg.addErrorMessage();
		}
		msg.addSuccessMessage();
		
		return msg;
	}
	
}
