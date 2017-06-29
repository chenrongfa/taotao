/**
 * 
 */
package com.taotao_search.test;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao_search.bean.ItemSearch;
import com.taotao_search.bean.Msg;
import com.taotao_search.mapper.ItemSearchMapper;
import com.taotao_search.service.ItemSearchService;
import com.taotao_search.service.SolorService;

/**
 * @description :测试Mapper能否用
 * @since 2017年6月22日下午7:04:46
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml", "classpath:spring/spring-service.xml" })
public class SearchTest {
	@Autowired
	ItemSearchMapper itemSearch;
	@Autowired
	SolorService solrService;
	@Autowired
	HttpSolrClient solrClient;
	@Autowired
	ItemSearchService itemSearchService;

	@Value("${base_url}")
	String url;

	@Test
	public void testMapper() {
		System.out.println(url);
		System.out.println(itemSearch.toString());
		List<ItemSearch> searchList = itemSearch.getItemSearchList();
		System.out.println(searchList.size());
	}

	@Test
	public void dataImport() {
		Msg msg = itemSearchService.dataImport();
		System.out.println(msg.getStatus());
	}

	@Test
	public void testClient() {
		System.out.println(solrClient);

	}
}
