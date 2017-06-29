/**
 * 
 */
package com.taotao_search.service;

import com.taotao_search.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月22日下午7:11:36
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface ItemSearchService {
	/**
	 *  数据 导入
	 * @return
	 */
	Msg dataImport();
	/**
	 *  solr分页查询
	 * @param q
	 * @param page
	 * @param rows
	 * @return
	 */
	Msg search(String q,long page,long rows);
	/**
	 *  solr 分页 并高亮
	 * @param q
	 * @param page
	 * @param rows
	 * @param hightField
	 * @return
	 */
	Msg search(String q,long page,long rows,String ...hightField);
	
	
	
}
