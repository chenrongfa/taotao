/**
 * 
 */
package com.taotao_rest.bean;

import java.util.List;

/**
 *description :商品分类结果
 * createTime:2017年6月15日 下午6:40:50
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public class ItemcatResult {
	
	private List<ItemcatNode> data;

	public List<ItemcatNode> getData() {
		return data;
	}

	public void setData(List<ItemcatNode> data) {
		this.data = data;
	}

}
