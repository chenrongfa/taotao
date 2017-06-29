/**
 * 
 */
package com.taotao_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao.bean.TbItemParamItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;

/**
 * @description :TODO
 * @since 2017年6月24日上午10:24:14
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */

@Service(value="tbitemparamService_rest")
public class TbitemparamServiceImpl implements TbitemparamService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Autowired
	private RedisService redisService;
	@Override
	public Msg getParamByItemId(long id, String key) {
		Msg msg=new Msg();
		String value = (String) redisService.getValue(key);
		if(value!=null){
			msg.addSuccessMessage();
			msg.getDataBean().put("itemparam", value);
			return msg;
		}
		TbItem item = itemMapper.selectById(id);
		if(item!=null&&item.getCid()<0){
			Long cid = item.getCid();
			TbItemParamItem paramItem = tbItemParamItemMapper.selectById(cid);
			if(paramItem!=null){
				if(paramItem.getParamData()!=null){
				String paramData = paramItem.getParamData();
				redisService.setValue(key, paramData);
				Boolean expire = redisService.setValueExpire(key, 60*60*24);
				if(expire){
					msg.addSuccessMessage();
					msg.getDataBean().put("itemparam", value);
					return msg;
				}
				}
			}
		}
		
		return msg.addErrorMessage();
	}

}
