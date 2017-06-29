/**
 * 
 */
package com.taotao_rest.service;

import com.taotao.bean.Msg;
import com.taotao.bean.TbItem;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:44:59
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface TbItemService {
	/**
	 *  通过id得到商品
	 * @param id
	 * @return
	 */
	Msg getItemById(long id); 
}
