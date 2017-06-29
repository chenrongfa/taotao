/**
 * 
 */
package com.taotao.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.Msg;
import com.taotao.bean.TbContent;
import com.taotao.service.dao.TbcontentService;

/**
 * description :TODO createTime:2017年6月16日 下午3:52:58 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Controller
public class TbContentController {
	@Autowired
	private TbcontentService tbcontentService;

	/**
	 * 分页查询
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIData getContentCategoryList(long categoryId, int page, int rows) {
		System.out.println("332");
		EasyUIData data = tbcontentService.getContentCategorysBypage(categoryId, page, rows);

		return data;
	}
	@RequestMapping("/content/save")
	@ResponseBody
	public Msg saveTbcontent(TbContent tbcontent){
		Msg msg=new Msg();
		boolean success = tbcontentService.save(tbcontent);
		if(success){
			msg.addSuccessMessage();
		}else{
			msg.addErrorMessage();
		}
		return msg;
	}
	@RequestMapping("/content/edit")
	@ResponseBody
	public Msg updateTbcontent(TbContent tbcontent){
		Msg msg=new Msg();
		boolean success = tbcontentService.updateById(tbcontent);
		if(success){
			msg.addSuccessMessage();
		}else{
			msg.addErrorMessage();
		}
		return msg;
	}
	@RequestMapping("/content/delete")
	@ResponseBody
	public Msg deleteTbcontentById(Long[] ids){
		Msg msg=new Msg();
		
		if(ids!=null){
			
			
			List<Long> id=Arrays.asList(ids);
			boolean success = tbcontentService.deleteByIds(id);
			if(success){
				msg.addSuccessMessage();
			}else{
				msg.addErrorMessage();
			}
		}
		
		return msg;
	}
}
