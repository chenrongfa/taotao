/**
 * 
 */
package com.taotao_rest.service;

import java.util.Map;

/**
 *description :TODO
 * createTime:2017年6月19日 下午1:06:45
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public interface RedisService {
	
	public void setValue(String key,String value);
	public Object getValue(String key);
	public void setBeanValue(String key,Object object);
	public Object getBeanValue(String key,Class cla);
	public Boolean setValueExpire(String key,long time);
	public long getValueTTL(String key);
	public void delValue(String key);
	public void setHashValue(String objectName,String key,String value);
	public Object getHashValue(String objectName,String key);
	public void setHashBeanValue(String objectName,String key,Object value);
	public Object getHashBeanValue(String objectName,String key,Class cls);
	public boolean delHashValue(String objectName,String... key);
	public Map<Object, Object> getHashMap(String key,Class<?> cls);
	public boolean putHashMap(String key,Map<Object, Object> map,Class<?> cls);
	public Map<Object, Object> getHashMap(Class<?> cls);
	public boolean putHashMap(Map<Object, Object> map,Class<?> cls);
}
