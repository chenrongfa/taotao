/**
 * 
 */
package com.taotao.bean;

/**
 * @description :购物车
 * @since 2017年6月26日上午11:24:12
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class Cart {

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
	
}
