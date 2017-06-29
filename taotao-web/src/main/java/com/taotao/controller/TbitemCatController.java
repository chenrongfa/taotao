package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao.bean.TbItemCat;
import com.taotao.service.dao.TbitemCatService;

@Controller
public class TbitemCatController {
	@Autowired
	private TbitemCatService itemcatSerive;

	/**
	 * 商品 查询
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/itemcat/list", method = RequestMethod.POST)
	public List<EasyuiTreeNode> getItemcatList(@RequestParam(value = "id", defaultValue = "0") int id) {
		System.out.println("1");
		List<EasyuiTreeNode> easyuiData = itemcatSerive.selectListEasyuiTreeByParentId(id);
		return easyuiData;
	}

	
}
