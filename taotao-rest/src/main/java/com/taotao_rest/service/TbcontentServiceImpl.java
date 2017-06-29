/**
 * 
 */
package com.taotao_rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.taotao.bean.TbContent;
import com.taotao.mapper.TbContentMapper;
import com.taotao_rest.service.RedisService;
import com.taotao_rest.service.TbcontentService;

/**
 * description :TODO createTime:2017年6月19日 下午2:13:41 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Component("tbcontentServiceImpl_rest")
public class TbcontentServiceImpl implements TbcontentService {
	@Autowired
	private TbContentMapper tbcontent;
	@Autowired
	private RedisService redisService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao_rest.service.TbcontentService#selectFromMysqlOrRedis(long)
	 */
	@Override
	public List<TbContent> selectFromMysqlOrRedis(long category, String key, String hashkey) {
		String format_key = String.format(hashkey + "%s", category + "");

		@SuppressWarnings("unchecked")
		List<TbContent> tbcontent = (List<TbContent>) redisService.getHashBeanValue(key, format_key, TbContent.class);

		if (tbcontent != null && tbcontent.size() > 0) {
			System.out.println(1);
			return tbcontent;
		}
		System.out.println(2);
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("category_id", category);
		// 查询数据库
		List<TbContent> lists = this.tbcontent.selectByMap(columnMap);
		if (lists != null && lists.size() > 0) {
			// 用redis 缓存
			redisService.setHashBeanValue(key, format_key, lists);
		}
		return lists;
	}

}
