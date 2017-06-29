/**
 * 
 */
package com.taotao.service;

import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月24日上午10:45:26
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class TbItemServiceImpl implements TbItemService {

	@Override
	public TbItem getItemById(String url) {
		// TODO Auto-generated method stub
		String json = HttpclientUtils.httpgetForString(url);
		Msg msg = JsonUtils.jsonToPojo(json, Msg.class);
		if(msg.getStatus()==200){
			String object = (String) msg.getDataBean().get("item");
			if(object!=null){
				TbItem tbItem = JsonUtils.jsonToPojo(object, TbItem.class);
				return tbItem;
			}
		}
		return null;
	}

}
