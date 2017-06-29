/**
 * 
 */
package com.taotao_rest.service;

import com.taotao.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月24日上午9:55:26
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface TbitemDescService {
	/**
	 *  通过id 查询 item descredis 没有在查询 mysql
	 * @param id
	 * @return
	 */
	Msg getItendescById(long id,String key);
}
