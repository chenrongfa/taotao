package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.TbItemCat;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.service.dao.TbitemCatService;

@Service
public class TbitemCatServiceimpl implements TbitemCatService {

	@Autowired
	private TbItemCatMapper itemcatMapper;
	@Override
	public List<TbItemCat> selectAll() {
		// TODO Auto-generated method stub
		List<TbItemCat> map = itemcatMapper.selectByMap(null);
		return map;
	}
	
	@Override
	public List<TbItemCat> selectListById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItemCat> selectListByParentId(long id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<>();
		map.put("parent_id", id);
		List<TbItemCat> selectByMap = itemcatMapper.selectByMap(map);
		return selectByMap;
	}
	
	
	public List<EasyuiTreeNode> selectListEasyuiTreeByParentId(long id) {
		// TODO Auto-generated method stub
		List<TbItemCat> selectListByParentId = selectListByParentId(id);
		List nodes = new ArrayList<>();
		if (selectListByParentId != null) {
			for (TbItemCat itemcat : selectListByParentId) {
				Map<String, Object> node = new HashMap<>();
				node.put("id", itemcat.getId());
				node.put("text", itemcat.getName());
				node.put("state", itemcat.getIsParent() ? "closed" : "open");
				nodes.add(node);
			}
		}
		return nodes;
		
	}

}
