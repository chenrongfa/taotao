/**
 * 
 */
package com.taotao_rest.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *description :商品分类节点
 * createTime:2017年6月15日 下午6:38:02
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public class ItemcatNode {
	@JsonProperty("n")
	private String next;
	@JsonProperty("u")
	private String union;
	@JsonProperty("i")
	private List<?> items;
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getUnion() {
		return union;
	}
	public void setUnion(String union) {
		this.union = union;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}

}
