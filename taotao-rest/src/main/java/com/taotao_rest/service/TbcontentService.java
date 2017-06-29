/**
 * 
 */
package com.taotao_rest.service;

import java.util.List;

import com.taotao.bean.TbContent;

/**
 *description :TODO
 * createTime:2017年6月19日 下午2:10:15
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public interface TbcontentService {
	

	/**
	 * @param category
	 * @param key
	 * @param hashkey
	 * @return
	 */
	List<TbContent> selectFromMysqlOrRedis(long category, String key, String hashkey);

}
