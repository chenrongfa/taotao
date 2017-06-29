package com.taotao.service.dao;

import java.util.List;

import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.TbItemCat;

public interface TbitemCatService {

	/**
	 *  查询所有
	 * @return
	 */
	List<TbItemCat> selectAll();
	/**
	 *  通过id查询
	 * @param id
	 * @return
	 */
	List<TbItemCat> selectListById(int id);
	/**
	 *  通过 parentid查询
	 * @param id
	 * @return
	 */
	List<TbItemCat> selectListByParentId(long id);
	
	List<EasyuiTreeNode> selectListEasyuiTreeByParentId(long id);
}
