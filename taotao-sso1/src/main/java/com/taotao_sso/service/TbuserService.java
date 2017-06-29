/**
 * 
 */
package com.taotao_sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.bean.Msg;
import com.taotao.bean.TbUser;

/**
 * @description :TODO
 * @since 2017年6月20日下午3:42:44
 * @see taotao-sso1
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public interface TbuserService {
	/**
	 *  
	 * @param userdata 要检验的数据
	 * @param type 检验什么 1 username;2 password; 3 email
	 * @param callback 有这么参数就返回jsonp
	 * @return
	 */
	Object checkUserData(String userdata,long type, String callback);

	/**
	 * @param user
	 * @return
	 */
	Msg insert(TbUser user);

	/**
	 *  用户登录并保存token 在redis中 并设置过期时间 and 设置cookies
	 * @param user
	 * @param user_key 
	 * @param response 
	 * @param request 
	 * @return
	 */
	Msg login(TbUser user, String user_key, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 通过Tokenkey 查询redis
	 * @param string
	 * @return
	 */
	Msg getToken(String tokenKey);

	/**
	 * 	
	 * 安全退出并删除 redis tokenKey 设置过期也可以的
	 * 设置 cookie过期
	 * @param string
	 * @return Msg
	 */
	Msg logout(String tokenKey,HttpServletRequest request,HttpServletResponse respons);
}
