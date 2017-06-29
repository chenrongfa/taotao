/**
 * 
 */
package com.taotao.bean;

import java.util.List;

/**
 * @description :TODO
 * @since 2017年6月26日下午12:39:34
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class CartResult {
 private	List<Cart> carts;
 //所有商品的总价格
  private long total;
public List<Cart> getCarts() {
	return carts;
}
public void setCarts(List<Cart> carts) {
	this.carts = carts;
}
public long getTotal() {
	return total;
}
public void setTotal(long total) {
	this.total = total;
}
  
}
