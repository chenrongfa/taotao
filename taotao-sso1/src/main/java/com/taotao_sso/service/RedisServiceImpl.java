/**
 * 
 */
package com.taotao_sso.service;



import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.taotao_sso.utils.RedisUtils;



/**
 * description :TODO createTime:2017年6月19日 下午1:13:54 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@Service
public class RedisServiceImpl implements RedisService ,InitializingBean{

	@Autowired
	private RedisTemplate<String, Object> key;
	ValueOperations<String, Object> opsForValue=null ;
	HashOperations<String, Object, Object> opsForHash=null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#setValue(java.lang.String,
	 * java.lang.String)
	 */
	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		opsForValue.set(key, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#getValue(java.lang.String)
	 */
	public Object getValue(String key) {
		// TODO Auto-generated method stub
	return	opsForValue.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#setBeanValue(java.lang.String,
	 * java.lang.Object)
	 */
	public void setBeanValue(String key, Object object) {
		// TODO Auto-generated method stub
		Class<? extends Object> class1 = object.getClass();
		RedisUtils.setJacksonValueSeriable(this.key, class1);
		opsForValue.set(key, object);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#getBeanValue(java.lang.String)
	 */
	public Object getBeanValue(String key,Class class2) {
		// TODO Auto-generated method stub
		
		RedisUtils.setJacksonValueSeriable(this.key, class2);
		Object object = opsForValue.get(key);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao_rest.service.RedisService#setValueExpire(java.lang.String,
	 * long)
	 */
	public Boolean setValueExpire(String key, long time) {
		Boolean expire = this.key.expire(key, time, TimeUnit.SECONDS);
	
		return expire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#getValueTTL(java.lang.String)
	 */
	public long getValueTTL(String key) {
		// TODO Auto-generated method stub
		
		return this.key.getExpire(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#delValue(java.lang.String)
	 */
	public void delValue(String key) {
		// TODO Auto-generated method stub
		this.key.delete(key);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#setHashValue(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void setHashValue(String objectName, String key, String value) {
		// TODO Auto-generated method stub
			opsForHash.put(objectName, key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#getHashValue(java.lang.String,
	 * java.lang.String)
	 */
	public Object getHashValue(String objectName, String key) {
		// TODO Auto-generated method stub
		Object object = opsForHash.get(objectName, key);
		return object;	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao_rest.service.RedisService#setHashBeanValue(java.lang.String,
	 * java.lang.String, java.lang.Object)
	 */
	public void setHashBeanValue(String objectName, String key, Object value) {
		// TODO Auto-generated method stub
		RedisUtils.setJacksonValueSeriable(this.key, value.getClass());
		
		opsForHash.put(objectName, key, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao_rest.service.RedisService#getHashBeanValue(java.lang.String,
	 * java.lang.String)
	 */
	public Object getHashBeanValue(String objectName, String key,Class cla) {
        RedisUtils.setJacksonValueSeriable(this.key, cla);
		
		Object object = opsForHash.get(objectName, key);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao_rest.service.RedisService#delHashValue(java.lang.String,
	 * java.lang.String)
	 */
	public void delHashValue(String objectName, String... key) {
		opsForHash.delete(objectName, key);
	
	}

	/* (non-Javadoc)
	 * @see com.taotao_rest.service.RedisService#getBeanValue(java.lang.String)
	 */


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(1);
		 opsForValue = key.opsForValue();
		 opsForHash = key.opsForHash();
	}
	public RedisServiceImpl(){
		System.out.println(2);
	}
	

	/* (non-Javadoc)
	 * @see com.taotao_rest.service.RedisService#getHashValue(java.lang.String, java.lang.String, java.lang.Class)
	 */

	


}
