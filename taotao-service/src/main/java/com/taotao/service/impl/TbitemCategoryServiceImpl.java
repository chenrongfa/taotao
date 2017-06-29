/**
 * 
 */
package com.taotao.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.taotao.bean.EasyUIData;
import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.TbContentCategory;
import com.taotao.bean.TbItemCat;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.service.dao.TbContentCategoryService;

/**
 * description :TODO createTime:2017年6月16日 上午11:13:41 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Service
public class TbitemCategoryServiceImpl implements TbContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao.service.dao.TbitemCategoryService#selectListByParentId(long)
	 */
	@Override
	public List<TbContentCategory> selectListByParentId(long id) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parent_id", id);
		List<TbContentCategory> selectByMap = contentCategory.selectByMap(columnMap);

		return selectByMap;
	}

	@Override
	public boolean deleteContentCategoryById(long id) {
		boolean result = true;
		TbContentCategory content = contentCategory.selectById(id);
		Integer deleteById = contentCategory.deleteById(id);
		if (deleteById < 0) {
			result = false;
			return result;
		}

		Map<String, Object> columnMap = new HashMap<>();
		Long parentId = content.getParentId();
		columnMap.put("parent_id", parentId);
		List<TbContentCategory> selectByMap = contentCategory.selectByMap(columnMap);
		if (selectByMap == null && selectByMap.size() == 0) {
			// 如果没有了子节点就把is_parent设置为false
			TbContentCategory parent = contentCategory.selectById(parentId);
			parent.setIsParent(false);
			int count = contentCategory.updateByPrimaryKeySelective(parent);
			result = count != 0;
		}
		return result;
	}

	@Override
	public List<EasyuiTreeNode> selectListEasyuiTreeByParentId(long id) {
		List<TbContentCategory> selectListByParentId = selectListByParentId(id);
		List nodes = new ArrayList<>();
		if (selectListByParentId != null) {
			for (TbContentCategory itemcat : selectListByParentId) {
				Map<String, Object> node = new HashMap<>();
				node.put("id", itemcat.getId());
				node.put("text", itemcat.getName());
				node.put("state", itemcat.getIsParent() ? "closed" : "open");
				nodes.add(node);
			}
		}
		return nodes;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao.service.dao.TbContentCategoryService#
	 * insertByCreateContentcategory(java.lang.String, long)
	 */
	@Override
	public long insertByCreateContentcategory(String text, long id) {
		// 创建对象
		TbContentCategory content = new TbContentCategory();
		content.setCreated(new Date());
		content.setUpdated(new Date());
		content.setParentId(id);
		content.setName(text);
		content.setIsParent(false);
		content.setSortOrder(1);
		// 1正常 2 删除
		content.setStatus(1);

		System.out.println("id+" + contentCategory.insertSelective(content));

		TbContentCategory parent = contentCategory.selectById(id);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			contentCategory.updateByPrimaryKeySelective(parent);
		}
		return content.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao.service.dao.TbContentCategoryService#updateNameByid(java.lang.
	 * String, long)
	 */
	@Override
	public boolean updateNameByid(String name, long id) {
		// TODO Auto-generated method stub
		TbContentCategory selectById = contentCategory.selectById(id);
		if (selectById != null) {
			selectById.setName(name);
		} else {
			return false;
		}
		int count = contentCategory.updateByPrimaryKeySelective(selectById);
		return count != 0;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.dao.TbContentCategoryService#getContentCategorysBypage(long, int, int)
	 */
	@Override
	public List<EasyUIData> getContentCategorysBypage(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
