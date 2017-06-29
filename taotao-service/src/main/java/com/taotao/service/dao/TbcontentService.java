/**
 * 
 */
package com.taotao.service.dao;

import java.util.List;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.TbContent;

/**
 *description :TODO
 * createTime:2017年6月16日 下午3:54:06
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public interface TbcontentService {
	/**
	 *  
	 * @param categoryId 商品id
	 * @param page 页数
	 * @param rows 页记录
	 * @return
	 */
	EasyUIData getContentCategorysBypage(long categoryId, int page, int rows);

	/**
	 * @param tbcontent
	 */
	boolean save(TbContent tbcontent);

	/**
	 * 通过ids 批量删除
	 * @param ids
	 * @return
	 */
	boolean deleteByIds(List<Long> ids);

	/**
	 * 通过id 更新
	 * @param tbcontent
	 * @return
	 */
	boolean updateById(TbContent tbcontent);
}
