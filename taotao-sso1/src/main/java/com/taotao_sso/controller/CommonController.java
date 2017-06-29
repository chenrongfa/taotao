/**
 * 
 */
package com.taotao_sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;

/**
 * @description :TODO
 * @since 2017年6月21日下午12:16:23
 * @see taotao-sso1
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Controller
public class CommonController {

	@RequestMapping("/{page}")
	public String login(@PathVariable("page")String page,String redirect,Model  mode){
		System.out.println(111);
		System.out.println(redirect);
		if(!StringUtils.isEmpty(redirect)){
			mode.addAttribute("redirect", redirect);
		}
		return page;
	}
	
}
