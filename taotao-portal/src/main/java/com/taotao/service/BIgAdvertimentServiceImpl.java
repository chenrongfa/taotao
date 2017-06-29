/**
 * 
 */
package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.bean.BIgAdvertiment;
import com.taotao.bean.TbContent;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 *description :TODO
 * createTime:2017年6月17日 下午12:36:10
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
@Service
public class BIgAdvertimentServiceImpl implements BIgAdvertimentService {

	/* (non-Javadoc)
	 * @see com.taotao.service.BIgAdvertimentService#getBIgAdvertimentByHttpcliean(java.lang.String)
	 */
	public List<BIgAdvertiment> getBIgAdvertimentByHttpcliean(String url) {
		String string = HttpclientUtils.httpgetForString(url);
		List<BIgAdvertiment> ads=null;
			if(string!=null){
		List<TbContent> jsonToList = JsonUtils.jsonToList(string, TbContent.class);
			ads=new ArrayList<>();
			if(jsonToList!=null&&jsonToList.size()>0){
			for(TbContent content:jsonToList){
				BIgAdvertiment ad=new BIgAdvertiment();
				ad.setAlt(content.getTitle());
				ad.setHeight(240);
				ad.setHeightB(240);
				ad.setWidth(670);
				ad.setWidthB(550);
				ad.setHref(content.getUrl());
				ad.setSrc(content.getPic());
				ad.setSrcB(content.getPic());
				ads.add(ad);
			}
			}
			}
		return ads;
	}

}
