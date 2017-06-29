package com.taotao.bean;

import java.io.Serializable;
import java.util.List;
/**
 *  easy 需要返回的数据格式
 * @author crf
 *
 */
public class EasyUIData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
