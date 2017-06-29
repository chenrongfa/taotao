/**
 * 
 */
package com.taotao_rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bean.TbItemCat;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao_rest.bean.ItemcatNode;
import com.taotao_rest.bean.ItemcatResult;

/**
 * description :TODO createTime:2017年6月15日 下午6:46:55 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Service
public class ItemcatNodeServiceImpl implements ItemcatNodeService {
	@Autowired
	private TbItemCatMapper itemcatMapper;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.ItemcatNodeService#getItemcatResult()
	 */

	public ItemcatResult getItemcatResult() {
		// TODO Auto-generated method stub
		List<ItemcatNode> listByParentId = (List<ItemcatNode>) getListByParentId(0);
		ItemcatResult itemcatResult = new ItemcatResult();
		itemcatResult.setData(listByParentId);
		return itemcatResult;
	}

	/**
	 * 递归所有的子节点
	 * 
	 * @param id
	 * @return
	 */
	private List<?> getListByParentId(long id) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parent_id", id);
		List<TbItemCat> selectByMap = itemcatMapper.selectByMap(columnMap);
		List nodes = new ArrayList<>();
		for (TbItemCat item : selectByMap) {
			// 商品下架
			if (item.getStatus() > 1) {
				continue;
			}
			ItemcatNode node = new ItemcatNode();
			if (item.getIsParent()) {
				if (id == 0) {
					node.setNext("<a href='/products/" + item.getId() + ".html'>" + item.getName() + "</a>");

				}
				node.setUnion("/products/" + item.getId() + ".html");
				node.setNext(item.getName());
				node.setItems(getListByParentId(item.getId()));
				nodes.add(node);
			} else {
				nodes.add("/products/" + item.getId() + ".html|" + item.getName());
			}
		}
		return nodes;
	}
}
