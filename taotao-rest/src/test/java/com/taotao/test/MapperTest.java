/**
 * 
 */
package com.taotao.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.bean.TbContent;
import com.taotao.bean.TbItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao_rest.bean.Cart;
import com.taotao_rest.bean.OrderCreate;
import com.taotao_rest.dao.OrderCreateMapper;
import com.taotao_rest.dao.UserOrdersMapper;
import com.taotao_rest.service.CartService;
import com.taotao_rest.service.RedisService;
import com.taotao_rest.utils.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *description :测试使用maven tomcat 是否需要资源转移
 * createTime:2017年6月15日 下午6:26:06
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-common.xml","classpath:spring/spring-service.xml"
		,"classpath:spring/spring-mybatis.xml"})
public class MapperTest {
	@Autowired
  private TbItemMapper item;
	@Autowired
	
	@Test
	public void testMapper(){
		TbItem selectById = item.selectById(1466156876);
		System.out.println(selectById.toString());
	}
	
	@Test
	public void testRedis(){
		System.out.println("15");
		Jedis jedis=new Jedis("192.168.123.164", 6380);
		
		System.out.println(jedis.get("d"));
		jedis.close();
	}
	@Test
	public void testRedisPool(){
		JedisPool pool=new JedisPool("192.168.123.164", 6380);
		Jedis jedis = pool.getResource();
		String set = jedis.set("name", "name");
		System.out.println(set);
		jedis.close();
		pool.close();
	}
	@Test
	public void testRedisRedistemplate(){
		
		
		JedisPool pool=new JedisPool("192.168.123.164", 6380);
		Jedis jedis = pool.getResource();
		String set = jedis.set("name", "name");
		System.out.println(set);
		jedis.close();
		pool.close();
	}
	@Test
	public void testStringformat(){
		String raw = "hello";
		String str = String.format("nihao%6s", raw);
		System.out.println(str);
		
	}
	@Autowired
	private RedisTemplate<String, Object> key;
	@Test
	public void testRedisTemplate(){
		 TbContent content=new TbContent();
		 content.setCategoryId(55l);
		 content.setCreated(new Date());
		 ValueOperations<String, Object> opsForValue = key.opsForValue();
		 key.setValueSerializer(new Jackson2JsonRedisSerializer<>(TbContent.class));
		  opsForValue.set("nihao1", content);
		TbContent content1= (TbContent) opsForValue.get("nihao");
		System.out.println(content1.toString());
		  key.setValueSerializer(new StringRedisSerializer());
		
		TbContent content2= (TbContent) opsForValue.get("nihao");
		System.out.println(content2.toString());
		
		
	}
	
	@Test
	public void testRedisTemplate1(){
		TbContent content=new TbContent();
		content.setCategoryId(55l);
		content.setCreated(new Date());
		
		RedisTemplate<String,TbContent> seriable = (RedisTemplate<String, TbContent>) RedisUtils.setJacksonValueSeriable(key, TbContent.class);
		ValueOperations<String, TbContent> opsForValue = seriable.opsForValue();
		TbContent content1= (TbContent) opsForValue.get("nihao");
		System.out.println(content1.toString());
		key.setValueSerializer(new StringRedisSerializer());
		
		
		
		
	}
	@Autowired
	private RedisService service;
	@Test
	public void testRedisService(){
			TbContent content=  (TbContent) service.getBeanValue("nihao",TbContent.class);
		
		System.out.println(content.toString());
	}

	@Autowired
	private RedisTemplate<String, Object> key1;

	@Test
	public void testhash(){
		key1.setValueSerializer(new Jackson2JsonRedisSerializer<>(Cart.class));
		HashOperations<String, Object, Object> opsForHash = key1.opsForHash();
		 for(int i=0;i<10;i++){
			 Cart cart=new Cart();
			 cart.setId(i);
			 cart.setImage("image"+i);;
			 cart.setTitle("title"+i);
			 opsForHash.put("key21", cart.getId()+"k", cart);
		 }
		
	}
	@Test
	public void testGetall(){
		key1.setValueSerializer(new Jackson2JsonRedisSerializer<>(Cart.class));
		HashOperations<String, Object, Object> opsForHash = key1.opsForHash();
		Map<Object, Object> entries = opsForHash.entries("cart");
		Set<Entry<Object,Object>> entrySet = entries.entrySet();
		Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<Object, Object> next = iterator.next();
			System.out.println("key"+next.getKey()+"value"+next.getValue().toString());
		}
	}
	@Test
	public void testputall1(){
		key1.setValueSerializer(new Jackson2JsonRedisSerializer<>(Cart.class));
		HashOperations<String, Object, Object> opsForHash = key1.opsForHash();
		Map<Object, Object> map = opsForHash.entries("key21");
		Cart cart=new Cart();
		cart.setId(1);
		cart.setTitle("陈荣发3");
		cart.setImage("陈荣发3");
		map.put("1k", cart);
		Cart cart1=new Cart();
		cart1.setId(0);
		cart1.setTitle("陈荣发");
		cart1.setImage("陈荣发");
		map.put("1k", cart);
		map.put("0k", cart1);
		opsForHash.putAll("key21", map);
		
	}
	@Test
	public void testIsrecover1(){
		key1.setValueSerializer(new Jackson2JsonRedisSerializer<>(Cart.class));
		HashOperations<String, Object, Object> opsForHash = key1.opsForHash();
		Cart cart=new Cart();
		cart.setId(1);
		cart.setTitle("陈荣发1");
		cart.setImage("陈荣发1");
		if(!opsForHash.putIfAbsent("key21", 1+"k", cart)){
			opsForHash.delete("key21", "1k");
			System.out.println("122");
			opsForHash.putIfAbsent("key21", 1+"k", cart);
		}
		
		
	}
	@Autowired
	private CartService cartService;
	@Test
	public void testCartservice(){
	/*
		cartService.addShopcar("dd", null);*/
		Cart cart=new Cart();
		cart.setId(1);
		cart.setTitle("陈荣发1");
		cart.setImage("陈荣发1");
		cartService.addShopcar(cart);
		
	}
	@Autowired
	private UserOrdersMapper userMapper;
	@Test
	public void testUserOrderMapper(){
		com.taotao_rest.bean.UserOrders userOrders = userMapper.selectById("1498537549366");
		System.out.println(userOrders.toString());
		
		
	}
	@Autowired
	private OrderCreateMapper orderCreateMapper;
	@Test
	public void testOrderMapper(){
		OrderCreate create = orderCreateMapper.selectByOrderId("1498537549366");
		System.out.println(create.toString());
		
		
	}
	
}
