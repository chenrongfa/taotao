/**
 * 
 */
package com.taotao_rest.dao;

import com.taotao_rest.bean.OrderCreate;

/**
 * @description :TODO
 * @since 2017年6月27日下午1:50:46
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface OrderCreateMapper {
 OrderCreate selectByOrderId(String id);
}
