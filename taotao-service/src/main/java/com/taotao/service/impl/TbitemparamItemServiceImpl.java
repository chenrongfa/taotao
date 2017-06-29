package com.taotao.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.TbItemParam;
import com.taotao.bean.TbItemParamItem;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.service.dao.TbitemparamItemService;
@Service
public class TbitemparamItemServiceImpl implements TbitemparamItemService {
	@Autowired
 private TbItemParamItemMapper itemParamitemMapper;
	public boolean saveByItemcatId(long itemid, String paramdata) {
		// TODO Auto-generated method stub
		//创建要插入的对象
			
				TbItemParamItem record=new TbItemParamItem();
				record.setItemId(itemid);
				record.setParamData(paramdata);
				record.setUpdated(new Date());
				record.setCreated(new Date());
				int selective = itemParamitemMapper.insertSelective(record);
				return selective!=-1;
		
	}

}
