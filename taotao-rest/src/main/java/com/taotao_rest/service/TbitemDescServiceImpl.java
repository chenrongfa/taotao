/**
 * 
 */
package com.taotao_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItemDesc;
import com.taotao.mapper.TbItemDescMapper;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:58:35
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service("tbitemDescServiceImpl_rest")
public class TbitemDescServiceImpl implements TbitemDescService {
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public Msg getItendescById(long id,String key) {
		Msg msg=new Msg();
		String value = (String) redisService.getValue(key);
		if(value!=null&&value.length()>0){
			msg.addSuccessMessage();
			msg.getDataBean().put("desc", value);
			return msg;
		}
		TbItemDesc itemDesc = itemDescMapper.selectById(id);
		if(itemDesc!=null){
			if(itemDesc.getItemDesc()!=null){
			redisService.setValue(key, itemDesc.getItemDesc());
			Boolean expire = redisService.setValueExpire(key, 60*60*24);
			if(expire){
				msg.addSuccessMessage();
				msg.getDataBean().put("desc", itemDesc.getItemDesc());
				return msg;
			}
			}
		}
		return msg.addErrorMessage();
	}

}
