/**
 * 
 */
package com.taotao.service;

import com.taotao.bean.ItemSearchResult;

/**
 * @description :TODO
 * @since 2017年6月23日下午3:55:21
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface ItemSearchService {
	/**
	 * 查询条件
	 * @param q
	 * @param page
	 * @return
	 */
	ItemSearchResult getItemInfo(String q,long page);


}
