package com.taotao.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.TbItemDesc;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.service.dao.TbitemDescService;

@Service
public class TbitemDescServiceImpl implements TbitemDescService {
	@Autowired
 private TbItemDescMapper itemDescMapper;
	public boolean save(long itemid, String desc) {
		TbItemDesc record=new TbItemDesc();
		record.setCreated(new Date());
		record.setUpdated(new Date());
		record.setItemDesc(desc);
		record.setItemId(itemid);
		int selective = itemDescMapper.insertSelective(record);
		return selective!=-1;
	}

}
