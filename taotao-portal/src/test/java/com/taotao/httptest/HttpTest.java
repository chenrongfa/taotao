/**
 * 
 */
package com.taotao.httptest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.taotao.bean.TbUser;
import com.taotao_portal.utils.HttpclientUtils;
import com.taotao_portal.utils.JsonUtils;

/**
 *description :httpclient 测试
 * createTime:2017年6月16日 下午6:47:22
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public class HttpTest {
  
	@Test
	public void httpget(){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response=null;
		HttpGet httpget=new HttpGet();
		try {
			httpget.setURI(new URI("http://localhost:8081/taotao-rest/tbcontent/89"));
			
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
		
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	}
	@Test
	public void httppot(){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost=new HttpPost();
	/*	HttpParams params=new BasicHttpParams();
		params.setParameter("name", "陈荣发");
		params.setParameter("age", 21);
		httppot.setParams(params);*/
		
		
		
		List<BasicNameValuePair> parameters=new ArrayList<>();
		BasicNameValuePair  base=new BasicNameValuePair("name", "陈荣发");
		BasicNameValuePair  base1=new BasicNameValuePair("age", "21");
			parameters.add(base1);	
			parameters.add(base);	
		HttpEntity entity=null;
		try {
			entity = new UrlEncodedFormEntity(parameters,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		httppost.setEntity(entity);
		
		try {
			httppost.setURI(new URI("http://localhost:8081/taotao-rest/tbcontent"));
			CloseableHttpResponse response = httpClient.execute(httppost);
			HttpEntity entity1 = response.getEntity();
			String string = EntityUtils.toString(entity1);
			System.out.println(string);
			
			response.close();
			httpClient.close();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void httppotForjson(){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost=new HttpPost("http://localhost:8081/taotao-rest/testjson");
		TbUser user=new TbUser();
		user.setPhone("18720979339");
		StringEntity test=null;
		String json=JsonUtils.objectToJson(user);
		try {
			test=new StringEntity(json, "application/json","utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httppost.setEntity(test);
		
		try {
			httpClient.execute(httppost);
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@Test
	public void testHttpUtils(){
		
		/*Map<String, String> param=new HashMap<>();
		param.put("name", "陈荣发");
		param.put("age", "21");*/
		//String string = HttpclientUtils.httpgetForString("http://localhost:8081/taotao-rest/tbcontent/89");
		String string = HttpclientUtils.httppost("http://localhost:8081/taotao-rest/tbcontent", "name", "陈荣发","age", "21");
		
		System.out.println(string);
		
	}
}
