package com.taotao.service.dao;

import java.util.List;

import com.taotao.bean.EasyUIData;
import com.taotao.bean.TbItemParam;

public interface TbitemparamService {
	
	EasyUIData getTbitemparamByPage(int page,int rows);
	
	List<TbItemParam> findAll();
	boolean deleItemparamByIds(String ids[]);
		
	TbItemParam findItemparamByItemcatId(int tbitemid);
	boolean saveByItemcatId(long itemcatid,String paramdata);
	
}
