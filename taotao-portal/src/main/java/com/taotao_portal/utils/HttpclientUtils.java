/**
 * 
 */
package com.taotao_portal.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.taotao.bean.TbUser;

/**
 * @description :网络请求类
 * @createTime:2017年6月16日 下午7:49:01
 * @project:ssm
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
public class HttpclientUtils {
	/**
	 * 返回字符窜
	 * 
	 * @param url
	 * @return
	 */
	public static String httpgetForString(String url) {
		return httpgetForStringWithParm(url, null);
	}

	/**
	 * 返回字符窜
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpgetForStringWithParm(String url, Map<String, Object> param) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet httpget = new HttpGet();

		if (param != null) {
			Set<Entry<String, Object>> entrySet = param.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> next = iterator.next();
				HttpParams params = new BasicHttpParams();
				params.setParameter(next.getKey(), next.getValue());
				httpget.setParams(params);
			}

		}
		try {
			httpget.setURI(new URI(url));

			response = (CloseableHttpResponse) httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
			return string;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param param
	 *            Map<k,v>
	 * @return
	 */
	public static String httppost(String url, Map<String, String> param) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		// 设置参数
		if (param != null) {
			List<BasicNameValuePair> parameters = new ArrayList<>();
			Set<Entry<String, String>> entrySet = param.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				BasicNameValuePair base = new BasicNameValuePair(next.getKey(), (String) next.getValue());
				parameters.add(base);
			}
			HttpEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(parameters, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			httppost.setEntity(entity);
		}

		try {
			httppost.setURI(new URI(url));

			response = httpClient.execute(httppost);
			HttpEntity entity1 = response.getEntity();
			String string = EntityUtils.toString(entity1);
			System.out.println(string);
			return string;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 
	 * @param url
	 * @param param
	 *            k v ...
	 * @return
	 */
	public static String httppost(String url, String... param) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		// 设置参数
		if (param != null && param.length > 1) {
			List<BasicNameValuePair> parameters = new ArrayList<>();

			for (int i = 0; i < param.length; i += 2) {
				BasicNameValuePair base = new BasicNameValuePair(param[i], param[i + 1]);
				parameters.add(base);
			}
			HttpEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(parameters, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			httppost.setEntity(entity);
		}

		try {
			httppost.setURI(new URI(url));

			response = httpClient.execute(httppost);
			HttpEntity entity1 = response.getEntity();
			String string = EntityUtils.toString(entity1);
			System.out.println(string);
			return string;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param json
	 *            json
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpPostWithJson(String url, String json) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		StringEntity test = null;

		try {
			test = new StringEntity(json, "application/json", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httppost.setEntity(test);

		try {
			CloseableHttpResponse response = httpClient.execute(httppost);
			String string = EntityUtils.toString(response.getEntity());
			return string;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}
	/**
	 * Cookie:JSESSIONID=5C127EEF0419EA538ECAD8125BBCD1B5; TT_TOKEN=0b38864a-1903-4d48-9f78-e42886bd4efb
	 * @param url
	 *            请求地址
	 * @param json
	 *            json
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpPostWithJsonAndCookie(String url, String json,String cookKey
			,String cookValue) {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Cookie", cookKey+"="+cookValue);
		
		
		StringEntity test = null;
		
		try {
			test = new StringEntity(json, "application/json", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httppost.setEntity(test);
		
		try {
			CloseableHttpResponse response = httpClient.execute(httppost);
			String string = EntityUtils.toString(response.getEntity());
			return string;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
}
