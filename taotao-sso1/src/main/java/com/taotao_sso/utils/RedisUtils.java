/**
 * 
 */
package com.taotao_sso.utils;

import java.nio.charset.Charset;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *description :TODO
 * createTime:2017年6月19日 下午12:51:39
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public class RedisUtils {
	/**
	 *  设置Value返回类型
	 * @param rp
	 * @param type
	 * @return
	 */
	public static RedisTemplate<String, ?> setJacksonValueSeriable(RedisTemplate<String, Object> rp,Class type){
		rp.setValueSerializer(new Jackson2JsonRedisSerializer<>(type));
		return rp;
	}
	public static RedisTemplate<String, String> setStringValueSeriable(RedisTemplate<String, ?> rp,Charset charset){
		if(charset==null)
		rp.setValueSerializer(new StringRedisSerializer() );
		else
			rp.setValueSerializer(new StringRedisSerializer(charset));
		return (RedisTemplate<String, String>) rp;
	}

}
