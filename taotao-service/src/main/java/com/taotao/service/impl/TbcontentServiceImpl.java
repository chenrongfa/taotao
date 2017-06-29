/**
 * 
 */
package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.bean.EasyUIData;
import com.taotao.bean.TbContent;
import com.taotao.bean.TbContentCategory;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.service.dao.TbcontentService;

/**
 * description :TODO createTime:2017年6月16日 下午3:54:29 project:ssm
 * 
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
@Service
public class TbcontentServiceImpl implements TbcontentService {
	@Autowired
	private TbContentCategoryMapper content;
	@Autowired
	private TbContentMapper tbcontent;

	@Override
	public EasyUIData getContentCategorysBypage(long categoryId, int page, int rows) {
		EntityWrapper<TbContent> ew = null;
		if (categoryId != 0) {
			ew = new EntityWrapper<>();
			// 如果不是零,取子节点 ;是零取全部
			List<Long> ids = getListCategoryId(categoryId);
			for (Long id : ids) {
				ew.or("category_id={0}", id);
			}
		}
		
		Page<TbContent> rowBounds = new Page<>(page, rows);
		PageHelper.startPage(page, rows);
		List<TbContent> selectPage = tbcontent.selectPage(rowBounds, ew);
		System.out.println("size" + selectPage.size() + selectPage.toString());
		//转化 eauidata
		EasyUIData data=new EasyUIData();
		List<TbContent> nodes=new ArrayList<>();
		PageInfo<TbContent> pageinfo=new PageInfo<>(selectPage);
		//PageList page1=(PageList)selectPage;
	   System.out.println("total"+pageinfo.getTotal());
		data.setTotal(pageinfo.getTotal());
		//data.setTotal(page1.getPaginator().getTotalCount());
		for(TbContent tb:selectPage){
			nodes.add(tb);
		}
		data.setRows(nodes);
		return data;
	}

	/**
	 * 递归 不断取出 子节点
	 * 
	 * @param categoryId
	 * @return
	 */
	private List<Long> getListCategoryId(long categoryId) {
		List<Long> ids = new ArrayList<>();

		TbContentCategory selectByMap1 = content.selectById(categoryId);
		if (selectByMap1.getIsParent()) {

			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("parent_id", categoryId);
			List<TbContentCategory> selectByMap = content.selectByMap(columnMap);
			for (TbContentCategory content : selectByMap) {
				if (content.getIsParent()) {
					ids.addAll(getListCategoryId(content.getId()));
				} else {
					ids.add(content.getId());
				}

			}
		} else {
			ids.add(selectByMap1.getId());
		}
		return ids;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.dao.TbcontentService#save(com.taotao.bean.TbContent)
	 */
	@Override
	public boolean save(TbContent tbcontent) {
		// TODO Auto-generated method stub
		int count = this.tbcontent.insertSelective(tbcontent);
		return count!=0;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.dao.TbcontentService#deleteByIds(java.util.List)
	 */
	@Override
	public boolean deleteByIds(List<Long> ids) {
		Integer count = tbcontent.deleteBatchIds(ids);
		return count!=0;
	}

	/* (non-Javadoc)
	 * @see com.taotao.service.dao.TbcontentService#updateById(com.taotao.bean.TbContent)
	 */
	@Override
	public boolean updateById(TbContent tbcontent) {
		int count = this.tbcontent.updateByPrimaryKeySelective(tbcontent);
		return count!=0;
	}
}
