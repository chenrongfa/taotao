package com.taotao.service.dao;

import com.taotao.bean.TbItem;

public interface TbitemDao {
	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	TbItem selectById(long id);

	/**
	 * 保存
	 * 
	 * @param item
	 * @return
	 */
	boolean save(TbItem item);

	/**
	 *  批量上架还是下架
	 * @param ids 商品id
	 * @param status 状态标识符
	 * @return
	 * @throws Exception
	 */
	boolean updateStatusByIds(String[] ids,byte status)throws Exception;
/**
 *  保存 商品 ,商品描述和规格
 * @param item
 * @param desc
 * @param param
 * @return
 * @throws Exception 
 */
	boolean save(TbItem item, String desc, String param) throws Exception;
}
