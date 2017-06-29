package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.bean.EasyUIData;
import com.taotao.bean.TbItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.dao.TbitemDao;
import com.taotao.service.dao.TbitemDescService;
import com.taotao.service.dao.TbitemparamItemService;
import com.taotao.service.dao.TbitemparamService;
import com.taotao.utils.IdUtils;

@Service
public class TbitemServiceimpl implements TbitemDao {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbitemDescService itemdescService;
	@Autowired
	private TbitemparamItemService tbitemParamService;

	@Override
	public TbItem selectById(long id) {
		// TODO Auto-generated method stub
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		System.out.println(tbItem.toString());
		return tbItem;
	}

	public EasyUIData getEasyUIDataByItem(int page, int rows) {

		EasyUIData data = null;
		PageHelper.startPage(page, rows);
		List<TbItem> selectAll = itemMapper.selectAll();
		if (selectAll != null && selectAll.size() > 0) {
			data = new EasyUIData();
			PageInfo<TbItem> pageinfo = new PageInfo<>(selectAll);
			data.setTotal(pageinfo.getTotal());
			List<TbItem> items = new ArrayList<>();
			for (TbItem item : selectAll) {
				items.add(item);
			}
			data.setRows(items);
		}

		return data;
	}

	public boolean deleteByIds(long id) {

		int key = itemMapper.deleteByPrimaryKey(id);

		return key != -1;
	}

	/**
	 * 保存商品 ,描述 和 规格
	 * @param param 
	 * @param desc 
	 * @throws Exception 
	 */
	@Override
	public boolean save(TbItem item, String desc, String param) throws Exception {
		// TODO Auto-generated method stub
		
		long id = IdUtils.getinstance().makeItemId();
		boolean save = itemdescService.save(id, desc);
		if(!save){
			throw new Exception("添加失败");
		}
		boolean success = tbitemParamService.saveByItemcatId(id, param);
		if(!success){
			throw new Exception("添加失败");
		}
		if (item.getId() == null) {
			item.setId(id);
		}
		if (item.getStatus() == null) {
			item.setStatus((byte) 1);
		}
		if (item.getCreated() == null) {
			item.setCreated(new Date());
		}
		if (item.getUpdated() == null) {
			item.setUpdated(new Date());
		}

		int insert = itemMapper.insert(item);

		return insert != -1;
	}

	@Override
	public boolean updateStatusByIds(String[] ids,byte status) throws Exception {
			int count=0;
		List<TbItem> items = new ArrayList<>();
		for (String id : ids) {
			TbItem tbitem = new TbItem();
			tbitem.setId(Long.parseLong(id));
			tbitem.setStatus(status);
			items.add(tbitem);
		}

		for (TbItem item : items) {
			// 下架

			int keySelective = itemMapper.updateByPrimaryKeySelective(item);
			if(keySelective!=-1){
				count++;
			}
		}
		boolean success=count==ids.length;
			if(!success){
				throw new Exception("下架错误");
			}
		return success;
	}

	@Override
	public boolean save(TbItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
