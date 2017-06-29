/**
 * 
 */
package com.taotao.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.bean.ItemSearchResult;
import com.taotao.bean.Msg;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月23日下午3:57:19
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {

	@Value("${taotao_search_url}")
	private String url;

	@Override
	public ItemSearchResult getItemInfo(String q, long page) {
		Map<String, Object> map = new HashMap<>();
		map.put("q", q);
		map.put("page", page);
		System.out.println(url);
		String json = HttpclientUtils.httpgetForString(url + "/search?q=" + q + "&page=" + page);
		Msg msg = JsonUtils.jsonToPojo(json, Msg.class);
		if (msg != null) {
			ItemSearchResult object = JsonUtils.jsonToPojo((String)(msg.getDataBean().get("item")), ItemSearchResult.class);

			return object;
		}
		return null;
	}

}
