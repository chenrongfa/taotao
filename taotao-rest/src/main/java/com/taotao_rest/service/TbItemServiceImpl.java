/**
 * 
 */
package com.taotao_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.dao.TbitemparamItemService;
import com.taotao.utils.JsonUtils;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:46:19
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service("tbItemServiceImpl_rest")
public class TbItemServiceImpl implements TbItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Override
	public Msg getItemById(long id) {
		Msg msg=new Msg();
		TbItem item = itemMapper.selectById(id);
		if(item!=null){
			msg.addSuccessMessage();
			msg.addDataBean("item", JsonUtils.objectToJson(item));
		}else{
			msg.addErrorMessage();
		}
		
		
		return msg;
	}

}
