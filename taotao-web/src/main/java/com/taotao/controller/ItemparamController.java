package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.Msg;
import com.taotao.bean.TbItemParam;
import com.taotao.service.dao.TbitemparamService;

@Controller
public class ItemparamController {
	@Autowired
	private TbitemparamService itemparamservice;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/itemParam/list")
	@ResponseBody
	public EasyUIData getEasyUIDataByItem(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
		System.out.println("jinlai?");
		EasyUIData easyUIData = itemparamservice.getTbitemparamByPage(page, rows);
		return easyUIData;
	}

	/**
	 * 通过id删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/itemparam/delete")
	@ResponseBody
	public Msg deleItemparamByIds(@RequestParam(value = "ids", required = false, defaultValue = "10") String ids) {
		Msg msg = new Msg();
		String id[] = null;
		System.out.println("jinlai?");
		if (ids == null) {
			return msg.addErrorMessage();
		}
		id = ids.split(",");
		boolean success = itemparamservice.deleItemparamByIds(id);
		if (success) {
			msg.addSuccessMessage();
		} else {
			msg.addErrorMessage();
		}

		return msg;
	}
/**
 *  通过id查询
 * @param itemcatid
 * @return
 */
	@RequestMapping("/itemparam/query/{itemcatid}")
	@ResponseBody
	public Msg findItemparamByItemcatId(@PathVariable(value = "itemcatid") int itemcatid) {
		Msg msg = new Msg();
		String id[] = null;
		System.out.println("jinlai?");

		TbItemParam param = itemparamservice.findItemparamByItemcatId(itemcatid);
		if (param != null) {
			msg.addSuccessMessage();
			msg.addDataBean("data", param.getParamData());
		} else {
			msg.addErrorMessage();
		}

		return msg;
	}
/**
 *  插入一条数据
 * @param itemcatid
 * @param paramdData
 * @return
 */
	@RequestMapping(value = "/itemparam/save/{itemcatid}", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveByItemcatId(@PathVariable(value = "itemcatid") int itemcatid,
			@RequestParam("paramData") String paramdData) {
		Msg msg = new Msg();
		String id[] = null;
		System.out.println("jinlai?");

		boolean success = itemparamservice.saveByItemcatId(itemcatid, paramdData);
		if (success) {
			msg.addSuccessMessage();

		} else {
			msg.addErrorMessage();
		}

		return msg;
	}
}
