/**
 * 
 */
package com.taotao_search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;

import com.taotao_search.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月22日下午7:58:01
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface SolorService {
	/**
	 *  数据添加
	 * @param type class
	 * @param count 多个提交
	 * @return
	 */
	Msg addBeans(List<?> type,int count);
	List<?> getBeans(SolrDocumentList doc);
	List<?> getBeans(Class<?> t,SolrDocumentList doc);
	/**
	 * @param hightField
	 */
	void setHight(SolrQuery query,String[] hightField);
	/**
	 * @param query
	 * @param hightField
	 * @param pre
	 * @param suffix
	 */
	void setHight(SolrQuery query, String[] hightField, String pre, String suffix);
}
