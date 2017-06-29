package com.taotao.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.utils.FileupLoadUtils;
import com.taotao.utils.JsonUtils;

@Controller
public class CommonController {
	// ftpserver参数注入
	@Value("${ftp_host}")
	private String host;
	@Value("${ftp_port}")
	private Integer port;
	@Value("${ftp_username}")
	private String username;
	@Value("${ftp_password}")
	private String password;
	@Value("${ftp_baseDir}")
	private String baseDir;
	@Value("${ftp_path}")
	private String path;

	/**
	 * go 首页
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String goToIndex() {
		return "index";
	}

	/**
	 * 
	 * @param page
	 *            index想要去的page
	 * @return
	 */
	@RequestMapping("/{pages}")
	public String goPage(@PathVariable("pages") String page) {

		return page;
	}

	/**
	 * 文件上传
	 * 
	 * @param upload
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile, HttpServletRequest request) {
		System.out.println(2);

		System.out.println(host);
		Map map = FileupLoadUtils.getinstance().uplaodfile(uploadFile, path,port, host, username, password,
				baseDir);
		String jsonString = JsonUtils.objectToJson(map);

		return jsonString;
	}
}
