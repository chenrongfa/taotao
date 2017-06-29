/**
 * 
 */
package com.taotao_rest.bean;

import java.io.Serializable;

/**
 * @description :购物车
 * @since 2017年6月26日上午11:24:12
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String image;
	private long price;
	private long id;
	/**
	 *  购买数量
	 */
	private int num;
	/**
	 *  标题
	 */
	private String title;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Cart [image=" + image + ", price=" + price + ", id=" + id + ", num=" + num + ", title=" + title + "]";
	}
	//只比较id就可以了
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
