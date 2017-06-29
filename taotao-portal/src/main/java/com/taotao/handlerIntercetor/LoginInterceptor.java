/**
 * 
 */
package com.taotao.handlerIntercetor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.taotao.bean.Msg;
import com.taotao.bean.TbUser;
import com.taotao_portal.utils.CookieUtils;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 * @description :访问资源时检查用户是否登录
 * @since 2017年6月21日下午3:08:37
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */

public class LoginInterceptor implements HandlerInterceptor{

	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("陈");
		//查询redis 是否有缓存
		/*HttpclientUtils.httpgetForString(url)*/
		//查询客户端有TT_TOKEN 是否为空
		Cookie[] cookie = request.getCookies();
		String value = CookieUtils.getCookieValue(request, "TT_TOKEN");
	
		
			if(StringUtils.isNotEmpty(value)){
				String string = HttpclientUtils.httpgetForString("http://127.0.0.1:8083/taotao-sso/user/token/"+value);
				
				Msg msg = JsonUtils.jsonToPojo(string, Msg.class);
				TbUser object = (TbUser) msg.getDataBean().get("data");
				if(object!=null){
					return true;
				}else{
					//去登录页面
					response.sendRedirect("http://127.0.0.1:8083/taotao-sso/login?redirect="+request.getRequestURL()+"?q="+request.getParameter("q"));
					return false;
				}
			}
			//去登录页面
			response.sendRedirect("http://127.0.0.1:8083/taotao-sso/login?redirect="+request.getRequestURL());
			return false;
		}
		
			
		
		
	

}
