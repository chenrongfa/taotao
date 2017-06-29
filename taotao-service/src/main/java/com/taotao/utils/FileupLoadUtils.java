package com.taotao.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileupLoadUtils {
	private static FileupLoadUtils upload = new FileupLoadUtils();

	private FileupLoadUtils() {
	};

	public static FileupLoadUtils getinstance() {
		return upload;
	}

	/*
	 * 直接 文件上传本web
	 */
	public Map uplaodfile(MultipartFile file, String path) {
		/**
		 * 目录不存在创建
		 */
		File exist = new File(path);
		if (!exist.exists()) {
			exist.mkdirs();
		}
		Map<String, Object> map = new HashMap<>();
		String filename = file.getOriginalFilename();
		int lastIndexOf = filename.lastIndexOf(".");
		String imageId = IdUtils.getinstance().makeImageId();
		String newName = imageId + filename.substring(lastIndexOf);
		File up = new File(path, newName);
		try {
			file.transferTo(up);
			map.put("error", 0);
			map.put("url", "http://127.0.0.1:8080/taotao-web/images/" + newName);
			return map;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error", 1);
			map.put("url", "文件上传错误");
		}

		return map;
	}

	/**
	 * 通过ftp上传到另一个服务器
	 * 
	 * @param file
	 *            上传的文件
	 * @param path
	 *            路径
	 * @param port
	 *            端口欧
	 * @param host
	 *            ip或者域名
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param baseDir
	 * @return
	 */
	public Map uplaodfile(MultipartFile file, String filePath, int port, String host, String username, String password,
			String baseDir) {
		Map<String, Object> map = new HashMap<>();
		String filename = file.getOriginalFilename();
		int lastIndexOf = filename.lastIndexOf(".");
		String imageId = IdUtils.getinstance().makeImageId();
		String newName = imageId + filename.substring(lastIndexOf);
		try {
			boolean uploadFile = FtpUtil.uploadFile(host, port, username, password, baseDir, filePath, newName,
					file.getInputStream());
			if (uploadFile) {
				map.put("error", 0);
				map.put("url", "http://"+host+baseDir+filePath+"/"+newName);
				return map;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		map.put("error", 1);
		map.put("url", "文件上传错误");

		return map;
	}
	public Map uplaodfile(MultipartFile file,  int port, String host, String username, String password
			) {
		Map<String, Object> map = new HashMap<>();
		String filename = file.getOriginalFilename();
		int lastIndexOf = filename.lastIndexOf(".");
		String imageId = IdUtils.getinstance().makeImageId();
		String newName = imageId + filename.substring(lastIndexOf);
		try {
			boolean uploadFile = FtpUtil.uploadFile(host, port, username, password, newName,
					file.getInputStream());
			if (uploadFile) {
				map.put("error", 0);
				map.put("url", "http://"+host+"/"+newName);
				return map;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		map.put("error", 1);
		map.put("url", "文件上传错误");
		
		return map;
	}
}
