package com.taotao.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
	 * 返回json的通用类
	 * 
	 * @author crf
	 * 
	 */
	public class Msg  implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/**
		 * 响应吗
		 */
		private int status;
		/**
		 * 返回描述
		 */
		private String message;

		private Map<String, Object> dataBean = new HashMap<String, Object>();

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Map<String, Object> getDataBean() {
			return dataBean;
		}

		public void setDataBean(Map<String, Object> dataBean) {
			this.dataBean = dataBean;
		}

		public Msg addSuccessMessage(int status, String message) {
			this.status = status;
			this.message = message;
			return this;
		}

		/**
		 * 默认
		 * 
		 */
		public Msg addSuccessMessage() {
			this.status = 200;
			this.message = "ok";
			return this;
		}

		/**
		 * 默认
		 * 
		 */
		public Msg addErrorMessage() {
			this.status = 100;
			this.message = "error";
			return this;
		}

		/**
		 * 
		 * @param status
		 * @param meassage
		 * @return
		 */
		public Msg addErrorMessage(int status, String meassage) {
			this.status = status;
			this.message = meassage;
			return this;
		}

		/**
		 * 添加数据
		 * 
		 * @param key
		 * @param object
		 * @return
		 */
		public Msg addDataBean(String key, Object object) {
			dataBean.put(key, object);
			return this;
		}

		
	}
		

