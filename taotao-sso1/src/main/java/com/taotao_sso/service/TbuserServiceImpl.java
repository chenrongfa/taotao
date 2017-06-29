/**
 * 
 */
package com.taotao_sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.taotao.bean.Msg;
import com.taotao.bean.TbUser;
import com.taotao.mapper.TbUserMapper;
import com.taotao.utils.JsonUtils;
import com.taotao_sso.utils.CookieUtils;

/**
 * @description :TODO
 * @since 2017年6月20日下午3:46:50
 * @see taotao-sso1
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class TbuserServiceImpl implements TbuserService {
	@Autowired
	private TbUserMapper tbuser;
	@Autowired
	private RedisService redisService;

	@Override
	public Object checkUserData(String userdata, long type, String callback) {
		Msg msg = new Msg();
		MappingJacksonValue jack = null;
		msg.getDataBean().put("data", false);
		// 是否是回调
		boolean isCallback = false;
		if (callback != null) {
			isCallback = true;
		}
		if (userdata == null) {
			msg.addErrorMessage(400, "缺少参数");
			if (isCallback) {
				jack = new MappingJacksonValue(msg);
				jack.setJsonpFunction(callback);
				return jack;
			} else {
				return msg;
			}
		}
		if (!(type == 1 || type == 2 || type == 3)) {
			msg.addErrorMessage(400, "要传递 1 ,2,3");
			if (isCallback) {
				jack = new MappingJacksonValue(msg);
				jack.setJsonpFunction(callback);
				return jack;
			} else {
				return msg;
			}
		}
		Map<String, Object> columnMap = new HashMap<>();
		if (type == 1)
			columnMap.put("username", userdata);
		else if (type == 2)
			columnMap.put("phone", userdata);
		else
			columnMap.put("email", userdata);
		List<TbUser> selectByMap = tbuser.selectByMap(columnMap);
		if (selectByMap.size() <= 0) {
			msg.getDataBean().put("data", true);

		}
		msg.addSuccessMessage();
		if (isCallback) {
			jack = new MappingJacksonValue(msg);
			jack.setJsonpFunction(callback);
			return jack;
		} else {
			return msg;
		}

	}

	@Override
	public Msg insert(TbUser user) {
		Msg msg = new Msg();
		// 查询用户和email phone 是否存在

		EntityWrapper<TbUser> wrapper = new EntityWrapper<>();
		wrapper.or("username={0}", user.getUsername());
		wrapper.or("email={0}", user.getEmail());
		wrapper.or("phone={0}", user.getPhone());
		Integer count = tbuser.selectCount(wrapper);
		if (count == 0) {
			// 添加数据
			user.setUpdated(new Date());
			user.setCreated(new Date());
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			int num = tbuser.insertSelective(user);
			if (num != 0)
				msg.addSuccessMessage();
			else {
				msg.addErrorMessage(100, "添加失败");

			}

		} else {
			return msg.addErrorMessage(100, "用户名或者email存在");
		}

		return msg;
	}

	/**
	 * 用户登录 返回token
	 */
	@Override
	public Msg login(TbUser user, String user_key,HttpServletRequest request, HttpServletResponse response) {
		System.out.println(user_key);
		Msg msg = new Msg();
		/*
		 * Map<String, Object> columnMap=new HashMap<>();
		 * columnMap.put("username", user.getUsername());
		 * columnMap.put("passwpr", user.getUsername());
		 */
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

		TbUser one = tbuser.selectOne(user);

		if (one != null) {
			System.out.println("one" + one.toString());
			msg.addSuccessMessage();
			String token = UUID.randomUUID().toString();
			msg.getDataBean().put("token", token);
			// 保存到redis中
			redisService.setBeanValue(user_key + token, one);
			// 过期一天
			redisService.setValueExpire(user_key + token, 60 * 60 * 24);
			//设置cookies
			CookieUtils.setCookie(request, response, "TT_TOKEN", token,60*60*24,"utf-8");

		} else {
			msg.addErrorMessage();
		}

		return msg;
	}

	@Override
	public Msg getToken(String tokenKey) {
		Msg msg=new Msg();
		System.out.println(tokenKey);
		TbUser user = (TbUser) redisService.getBeanValue(tokenKey, TbUser.class);
		
		if(user!=null){
			msg.addSuccessMessage();
			msg.getDataBean().put("data", user);
		}else{
			msg.addErrorMessage();
		}
		return msg;
	}

	@Override
	public Msg logout(String tokenKey,HttpServletRequest request,HttpServletResponse response) {
		Msg msg=new Msg();
		Boolean expire = redisService.setValueExpire(tokenKey, 0);
		if(expire){
			msg.addSuccessMessage();
			CookieUtils.setCookie(request, response, "TT_TOKEN", null, 0);
		}else{
			msg.addErrorMessage();
		}
		
		return msg;
	}

}
