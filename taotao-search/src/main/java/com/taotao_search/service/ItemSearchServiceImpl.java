/**
 * 
 */
package com.taotao_search.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao_search.bean.ItemSearch;
import com.taotao_search.bean.ItemSearchResult;
import com.taotao_search.bean.Msg;
import com.taotao_search.mapper.ItemSearchMapper;
import com.taotao_search.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月22日下午8:28:12
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
	@Autowired
	private ItemSearchMapper itemseachMapper;
	@Autowired
	private SolorService solrservice;
	@Value("${hight_pre}") 
	private String pre; //高亮前缀
	@Value("${hight_suffix}")
	private String suffix; //高亮后缀
	
	@Autowired
	private HttpSolrClient solrClient;
	@Override
	public Msg dataImport() {
		try {
			List<ItemSearch> itemSearchList = itemseachMapper.getItemSearchList();
			int size = itemSearchList.size();
			if (size > 1000)
				solrservice.addBeans(itemSearchList, 100);
			else {
				solrservice.addBeans(itemSearchList, 0);
			}
		} catch (Exception e) {
			return new Msg().addErrorMessage(100, e.getMessage());
		}
		return new Msg().addSuccessMessage();
	}

	@Override
	public Msg search(String q, long page, long rows) {
		Msg search = search(q, page, rows,null);
		return search;
	}

	@Override
	public Msg search(String q, long page, long rows, String... hightField) {
		Msg msg=new Msg();
		ItemSearchResult searchResult=new ItemSearchResult();
		SolrQuery query=new SolrQuery(q);
		query.add("df","item_default_search");
		if(hightField!=null&&hightField.length>0){
			query.add("hl.usePhraseHighlighter", "true");
			solrservice.setHight(query,hightField,pre,suffix);
		}
		query.setStart((int) ((page-1)*rows));
		query.setRows((int)rows);
		/*solrservice.query(query);*/
		try {
			QueryResponse response = solrClient.query(query);
			List<ItemSearch> lists = response.getBeans(ItemSearch.class);
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			for(ItemSearch item:lists){
				//填充高亮信息
			System.out.println(item.toString());
			Map<String, List<String>> map = highlighting.get(item.getId()+"");
			List<String> list = map.get("item_title");
			if(list!=null&&list.size()>0){
			System.out.println("+1"+list.get(0));
				item.setItem_title(list.get(0));
			}
			}
			System.out.println("nihao"+lists.get(0));
			searchResult.setItem(lists);
			long numFound = response.getResults().getNumFound();
			searchResult.setTotal(numFound);
			long pages=0;
			if(numFound>rows){
				pages=numFound%rows>0?numFound/rows+1:numFound/rows;
			}else{
				pages=1;
			}
			
			searchResult.setPageCount(pages);
			searchResult.setCurrentPage(page);
			msg.getDataBean().put("item",JsonUtils.objectToJson(searchResult));
			msg.addSuccessMessage();
			
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			msg.addErrorMessage(100, e.toString());
			e.printStackTrace();
		}
		
		return msg;
	}

}
