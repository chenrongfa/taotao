/**
 * 
 */
package com.taotao.service;

import com.taotao.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月26日下午8:16:25
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface OrderService {
   Msg createOrder(String json);
   Msg createOrder(String json,String cookKey,String cookValue);
}
