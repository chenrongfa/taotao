/**
 * 
 */
package com.taotao.service.dao;

import java.util.List;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.EasyuiTreeNode;
import com.taotao.bean.TbContentCategory;
import com.taotao.bean.TbItemCat;

/**
 *description :TODO
 * createTime:2017年6月16日 上午11:11:21
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public interface TbContentCategoryService {

	/**
	 *  通过 parentid查询
	 * @param id
	 * @return
	 */
	List<TbContentCategory> selectListByParentId(long id);
	
	List<EasyuiTreeNode> selectListEasyuiTreeByParentId(long id);
	
	long insertByCreateContentcategory(String text,long id);

	/**
	 * 通过id 更新 name
	 * @param name
	 * @param id
	 * @return
	 */
	boolean updateNameByid(String name, long id);

	/**
	 * 通过id 删除 实体类记录
	 * @param id
	 * @return
	 */
	boolean deleteContentCategoryById(long id);

	/**
	 * 通过categotyId 来递归分页查询
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	List<EasyUIData> getContentCategorysBypage(long categoryId, int page, int rows);
}
