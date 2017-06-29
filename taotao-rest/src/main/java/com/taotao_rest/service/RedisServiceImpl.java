/**
 * 
 */
package com.taotao_rest.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.taotao_rest.utils.RedisUtils;

/**
 * description :TODO createTime:2017年6月19日 下午1:13:54 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Service
public class RedisServiceImpl implements RedisService, InitializingBean {

	@Autowired
	private RedisTemplate<String, Object> key;
	ValueOperations<String, Object> opsForValue = null;
	HashOperations<String, Object, Object> opsForHash = null;

	@Value("${cart_key}")
	private String cart_key;

	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		opsForValue.set(key, value);

	}

	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return opsForValue.get(key);
	}

	public void setBeanValue(String key, Object object) {
		// TODO Auto-generated method stub
		Class<? extends Object> class1 = object.getClass();
		RedisUtils.setJacksonValueSeriable(this.key, class1);
		opsForValue.set(key, object);

	}

	public Object getBeanValue(String key, Class class2) {
		// TODO Auto-generated method stub

		RedisUtils.setJacksonValueSeriable(this.key, class2);
		Object object = opsForValue.get(key);
		return object;
	}

	public Boolean setValueExpire(String key, long time) {
		Boolean expire = this.key.expire(key, time, TimeUnit.MILLISECONDS);

		return expire;
	}

	public long getValueTTL(String key) {
		// TODO Auto-generated method stub

		return this.key.getExpire(key);
	}

	public void delValue(String key) {
		// TODO Auto-generated method stub
		this.key.delete(key);

	}

	public void setHashValue(String objectName, String key, String value) {
		// TODO Auto-generated method stub
		opsForHash.put(objectName, key, value);
	}

	public Object getHashValue(String objectName, String key) {
		// TODO Auto-generated method stub
		Object object = opsForHash.get(objectName, key);
		return object;
	}

	public void setHashBeanValue(String objectName, String key, Object value) {
		// TODO Auto-generated method stub
		RedisUtils.setJacksonValueSeriable(this.key, value.getClass());

		opsForHash.put(objectName, key, value);

	}

	public Object getHashBeanValue(String objectName, String key, Class cla) {
		RedisUtils.setJacksonValueSeriable(this.key, cla);

		Object object = opsForHash.get(objectName, key);
		return object;
	}

	public boolean delHashValue(String objectName, String... key) {
		try {
			opsForHash.delete(objectName, key);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
			return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(1);
		opsForValue = key.opsForValue();
		opsForHash = key.opsForHash();
	}

	public RedisServiceImpl() {
		System.out.println(2);
	}

	@Override
	public Map<Object, Object> getHashMap(String key, Class<?> cls) {
		RedisUtils.setJacksonValueSeriable(this.key, cls);
		Map<Object, Object> entries = null;
		if (key != null)
			entries = opsForHash.entries(key);
		else {
			entries = getHashMap(cls);
		}

		return entries;
	}

	@Override
	public boolean putHashMap(String key, Map<Object, Object> map, Class<?> cls) {
		RedisUtils.setJacksonValueSeriable(this.key, cls);
		try {
			if(key!=null)
			opsForHash.putAll(key, map);
			else 
				return putHashMap(map, cls);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return true;
	}

	@Override
	public Map<Object, Object> getHashMap(Class<?> cls) {
		RedisUtils.setJacksonValueSeriable(key, cls);
		return opsForHash.entries(cart_key);
	}

	@Override
	public boolean putHashMap(Map<Object, Object> map, Class<?> cls) {
		RedisUtils.setJacksonValueSeriable(key, cls);
		try {
			opsForHash.putAll(cart_key, map);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
