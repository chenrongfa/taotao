/**
 * 
 */
package com.taotao_sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.taotao.bean.Msg;
import com.taotao.bean.TbUser;
import com.taotao_sso.service.TbuserService;

/**
 * @description :用户注册或者单点登录功能
 * @since 2017年6月20日 下午3:10:46
 * @see project:ssm
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class TbuserController {
	@Autowired
	private TbuserService tbuserService;
	@Value("${tbuser_cache_key}")
	private String user_key;
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable("param") String param, @PathVariable("type") long type,
			@RequestParam(value = "callback", required = false) String callback) {
		System.out.println(callback);
		Object checkdata = tbuserService.checkUserData(param, type, callback);
		return checkdata;
	}

	/**
	 * 使用jsr 303 验证
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping("/user/register")
	@ResponseBody
	public Msg register(@Valid TbUser user, BindingResult result) {

		Msg msg = new Msg();
		if (result.hasFieldErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				msg.getDataBean().put(error.getField(), error.getDefaultMessage());
				msg.addErrorMessage();

			}
			return msg;
		}
		System.out.println(user.toString());

		Msg data = tbuserService.insert(user);
		return data;
	}

	/**
	 * 测试
	 * 
	 * @param user
	 * @param result
	 * @return
	 */

	@RequestMapping("/user/validator")
	@ResponseBody
	public Msg validator(@Valid TbUser user, BindingResult result) {
		Msg msg = new Msg();
		if (result.hasFieldErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				msg.getDataBean().put(error.getField(), error.getDefaultMessage());
				msg.addErrorMessage();

			}
			return msg;
		}
		System.out.println(user.toString());

		Msg data = tbuserService.insert(user);
		return data;
	}

	@RequestMapping("/user/login")
	@ResponseBody
	public Msg userLogin(@Valid TbUser user, BindingResult result,HttpServletRequest request,
			HttpServletResponse response,Model model,String redirect) {
	
		System.out.println(user_key);
		System.out.println("dds");
		Msg msg = new Msg();
		if (result.hasFieldErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				//不验证手机和邮箱
				if ("email".equals(error.getField()) || "phone".equals(error.getField())) {
					msg.getDataBean().put(error.getField(), error.getDefaultMessage());
					msg.addErrorMessage();
				}

			}
			return msg;
		}
		System.out.println(user.toString() + "22");

		Msg data = tbuserService.login(user,user_key,request,response);
		return data;
	}
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object userLogin(@PathVariable("token") String token, String callback) {
		System.out.println(user_key);
		
		System.out.println(11);
		
		Msg data = tbuserService.getToken(user_key+token);
		if(callback==null){
			return data;
		}else{
			MappingJacksonValue jack=new MappingJacksonValue(data);
			jack.setJsonpFunction(callback);
			return jack;
		}
	
	}
	@RequestMapping("/user/logout/{token}")
	@ResponseBody
	public Object userLogout(@PathVariable("token") String token, String callback,
			HttpServletRequest request,HttpServletResponse response) {
		System.out.println(user_key);
		
		System.out.println(11);
		
		Msg data = tbuserService.logout(user_key+token,request,response);
		if(callback==null){
			return data;
		}else{
			MappingJacksonValue jack=new MappingJacksonValue(data);
			jack.setJsonpFunction(callback);
			return jack;
		}
		
	}

}
