/**
 * 
 */
package com.taotao_rest.service;

import com.taotao.bean.Msg;

/**
 * @description :TODO
 * @since 2017年6月24日上午10:19:16
 * @see taotao-rest
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface TbitemparamService {
	/**
	 *  先用key 查询redis 有返回,没有通过商品id 查询商品类别 去param
	 * @param id
	 * @param key
	 * @return
	 */
	Msg getParamByItemId(long id,String key);
}
