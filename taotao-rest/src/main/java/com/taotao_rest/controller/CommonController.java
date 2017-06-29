/**
 * 
 */
package com.taotao_rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taotao.bean.Msg;
import com.taotao.bean.TbContent;
import com.taotao.bean.TbUser;
import com.taotao.mapper.TbContentMapper;
import com.taotao.utils.JsonUtils;
import com.taotao_rest.bean.ItemcatNode;
import com.taotao_rest.bean.ItemcatResult;
import com.taotao_rest.service.ItemcatNodeService;
import com.taotao_rest.service.TbcontentService;

/**
 *description:TODO
 * createTime:2017年6月15日 下午7:10:54
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
@Controller
public class CommonController {
	@Value("${tbcontent_key}")
	private String content_key;
	@Value("${tbcontent_hashkey}")
	private String content_haskkey;
	@Autowired
	private com.taotao_rest.service.RedisService redisservice;
	
	@Autowired
	private ItemcatNodeService itemcatNode;
	@Autowired
	private TbcontentService tbcontent;
	@RequestMapping(value="/itemcat/all")
	@ResponseBody
	public  JSONPObject getItemcatResult(String callback){
		System.out.println(callback);
		
		ItemcatResult itemcatResult = itemcatNode.getItemcatResult();
		
		JSONPObject jsonp=new JSONPObject(callback, itemcatResult);
		
		
		
		return jsonp;
	}
	/**
	 *  通过id 查询内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/tbcontent/{id}")
	@ResponseBody
	public  List<TbContent> getItemcatResult(@PathVariable("id") long id){
		
		System.out.println(content_key);
		System.out.println(content_haskkey);
		
		List<TbContent> selectByMap = tbcontent.selectFromMysqlOrRedis(id, content_key, content_haskkey);
		
		return selectByMap;
	}
	/**
	 *  提供接口  当内容修改时删除redis里面的缓存
	 * @return
	 */
	@RequestMapping("/redis/delete/{category}")
	@ResponseBody
	public Msg deleteRedisContent(long category){
		String format_key = String.format(content_haskkey+"%s", category);
		redisservice.delHashValue(content_key, format_key);
		
		return new Msg().addSuccessMessage();
	}
	
	/*@RequestMapping(value="/itemcat/all",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  String getItemcatResult(String callback){
		System.out.println(callback);
		
		ItemcatResult itemcatResult = itemcatNode.getItemcatResult();
		String json=JsonUtils.objectToJson(itemcatResult);
		String call=callback+"("+json+")";
		
		
		
		return call;
	}*/
	/*@RequestMapping(value="/itemcat/all")
	@ResponseBody
	public  MappingJacksonValue getItemcatResult1(String callback){
		System.out.println(callback);
		
		ItemcatResult itemcatResult = itemcatNode.getItemcatResult();
		
		MappingJacksonValue jack=new  MappingJacksonValue(itemcatResult);
		
		jack.setJsonpFunction(callback);
		
		
		return jack;
	}*/
	@RequestMapping("/testjson")
	public String testjson(@RequestBody String json){
		System.out.println(json);
		return null;
	}
}
