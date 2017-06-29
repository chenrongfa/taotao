/**
 * 
 */
package com.taotao_search.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao_search.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月22日下午8:02:12
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class SolorServiceImpl implements SolorService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_search.service.SolorService#addBeans(java.util.List, int)
	 */
	@Autowired
	private HttpSolrClient solr;

	public Msg addBeans(List<?> type, int count) {
		Msg msg=new Msg();
		boolean isError=false;
		try {
		if(count==0){
				solr.addBeans(type);
				
			}
		else{
			
				solr.addBeans( type, count);
			}}
		catch (SolrServerException | IOException e) {
			isError=true;
				msg.addErrorMessage();
				e.printStackTrace();
			}finally{
				try {
					solr.commit();
					solr.close();
					//上面没有出错就 添加 防止覆盖
					if(!isError)
					msg.addSuccessMessage();
				} catch (SolrServerException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.addErrorMessage();
				}
			}
		
		return msg;
	}

	public List<?> getBeans(Class<?> t,SolrDocumentList doc) {
		DocumentObjectBinder binder=new DocumentObjectBinder();
		List<?> beans = binder.getBeans(t, doc);
				
		return beans;
	}

	/* (non-Javadoc)
	 * @see com.taotao_search.service.SolorService#getBeans(org.apache.solr.common.SolrDocumentList)
	 */
	@Override
	public List<?> getBeans(SolrDocumentList doc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.taotao_search.service.SolorService#setHight(org.apache.solr.client.solrj.SolrQuery, java.lang.String[])
	 */
	@Override
	public void setHight(SolrQuery query, String[] hightField) {
		setHight(query, hightField, null, null);
		
	}

	/* (non-Javadoc)
	 * @see com.taotao_search.service.SolorService#setHight(org.apache.solr.client.solrj.SolrQuery, java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public void setHight(SolrQuery query, String[] hightField, String pre, String suffix) {
		// TODO Auto-generated method stub
		query.setHighlight(true);
		if(hightField!=null&&hightField.length>0){
			for(String field: hightField){
				//添加高亮域
				query.add("hl.fl", field);
			}
			
		}
		if(pre==null){
			query.setHighlightSimplePre("<em style='color:red;font:20px'>");
		}else{
			query.setHighlightSimplePre(pre);
		}
		
		if(suffix==null){
			query.setHighlightSimplePost("</em>");
		}else{
			query.setHighlightSimplePost(suffix);
		}
		
	}

}
