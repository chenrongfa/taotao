/**
 * 
 */
package com.taotao.bean;

import java.io.Serializable;

/**
 *description :easyui 树节点需要的数据格式
 * createTime:2017年6月16日 上午10:49:26
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public class EasyuiTreeNode  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String text;
	private boolean state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "EasyuiTreeNode [id=" + id + ", text=" + text + ", state=" + state + ", getId()=" + getId()
				+ ", getText()=" + getText() + ", isState()=" + isState() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
