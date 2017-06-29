package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.bean.EasyUIData;
import com.taotao.bean.TbItemParam;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.service.dao.TbitemparamService;

@Service
public class TbitemparamImpl implements TbitemparamService {
	@Autowired
	private TbItemParamMapper itemparamMapper;

	/**
	 * 分页查询
	 */
	@Override
	public EasyUIData getTbitemparamByPage(int page, int rows) {
		// TODO Auto-generated method stub
		EasyUIData easyui = new EasyUIData();
		PageHelper.startPage(page, rows);
		List<TbItemParam> findAll = findAll();
		System.out.println(findAll.toString());
		List<TbItemParam> datas = new ArrayList<>();
		if (findAll != null && findAll.size() > 0) {
			easyui.setTotal(findAll.size());

			for (TbItemParam data : findAll) {
				datas.add(data);
			}
		}
		PageInfo<TbItemParam> params = new PageInfo<>(findAll);
		System.out.println(params.getList());
		easyui.setRows(datas);
		return easyui;
	}

	@Override
	public List<TbItemParam> findAll() {

		return itemparamMapper.selectByMap(null);
	}

	/**
	 * 批量删除
	 */
	@Override
	public boolean deleItemparamByIds(String[] ids) {
		// TODO Auto-generated method stub
		Integer deleteBatchIds = itemparamMapper.deleteBatchIds(Arrays.asList(ids));

		return deleteBatchIds != -1;
	}

	/**
	 * 通过商品种类id查询
	 */
	@Override
	public TbItemParam findItemparamByItemcatId(int tbitemid) {
		// TODO Auto-generated method stub
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("item_cat_id", tbitemid);

		List<TbItemParam> selectByMap = itemparamMapper.selectByMap(columnMap);
		if (selectByMap != null && selectByMap.size() > 0) {
			return selectByMap.get(0);
		}

		return null;
	}

	@Override
	public boolean saveByItemcatId(long itemcatid, String paramdata) {
		//创建要插入的对象
		TbItemParam record = new TbItemParam();
		record.setParamData(paramdata);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		record.setItemCatId( itemcatid);
		int selective = itemparamMapper.insertSelective(record);
		return selective!=-1;
	}

}
