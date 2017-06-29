/**
 * 
 */
package com.taotao_search.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @description :商品搜索类
 * @since 2017年6月22日下午6:20:05
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class ItemSearch {
	@Field
	private String id;
	@Field
	private String item_title;
	@Field
	private String item_image;
	@Field
	private String item_sell_point;
	@Field
	private long item_price;
	@Field
	private String item_desc;
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	@Field
	private String item_category_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem_title() {
		return item_title;
	}
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	public String getItem_image() {
		return item_image;
	}
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}
	public String getItem_sell_point() {
		return item_sell_point;
	}
	public void setItem_sell_point(String item_sell_point) {
		this.item_sell_point = item_sell_point;
	}
	public long getItem_price() {
		return item_price;
	}
	public void setItem_price(long item_price) {
		this.item_price = item_price;
	}
	public String getItem_category_name() {
		return item_category_name;
	}
	public void setItem_category_name(String item_category_name) {
		this.item_category_name = item_category_name;
	}
	@Override
	public String toString() {
		return "ItemSearch [id=" + id + ", item_title=" + item_title + ", item_image=" + item_image
				+ ", item_sell_point=" + item_sell_point + ", item_price=" + item_price + ", item_desc=" + item_desc
				+ ", item_category_name=" + item_category_name + "]";
	}

	
	
	
}
