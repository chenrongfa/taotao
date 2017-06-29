/**
 * 
 */
package com.taotao_search.bean;

import java.util.List;

/**
 * @description :分页结果bean
 * @since 2017年6月23日上午9:55:06
 * @see taotao-search
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class ItemSearchResult {
	//总记录
	private long total;
	/**
	 *  商品信息
	 */
	private List<ItemSearch> item;
	
	private long pageCount;
	private long currentPage;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<ItemSearch> getItem() {
		return item;
	}
	public void setItem(List<ItemSearch> item) {
		this.item = item;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "ItemSearchResult [total=" + total + ", item=" + item + ", pageCount=" + pageCount + ", currentPage="
				+ currentPage + "]";
	}
	
	
}
