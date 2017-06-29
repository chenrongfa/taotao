/**
 * 
 */
package com.taotao.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.taotao.bean.TbContent;
import com.taotao.bean.TbContentCategory;
import com.taotao.bean.TbItem;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.mapper.TbItemMapper;

/**
 * description :测试使用maven tomcat 是否需要资源转移 createTime:2017年6月15日 下午6:26:06
 * project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-common.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-mybatis.xml" })
public class MapperTest {
	@Autowired
	private TbItemMapper item;
	@Autowired
	private TbContentCategoryMapper content;
	@Autowired
	private TbContentMapper tbcontent;

	@Test
	public void test() {
		TbItem selectById = item.selectById(1466156876);
		System.out.println(selectById.toString());
	}

	@Test
	public void test1() {
		System.out.println(getListCategoryId(30).toString());
		List<Long> listCategoryId = getListCategoryId(30);
		EntityWrapper<TbContent> content1 = new EntityWrapper<>();
		for (long id : listCategoryId) {
			content1.or("category_id={0}", id);

		}
		System.out.println(content1.getSqlSegment());
		List<TbContent> selectList = tbcontent.selectList(content1);
		for (TbContent tb : selectList)
			System.out.println(tb.toString());
	}

	private List<Long> getListCategoryId(long categoryId) {
		List<Long> ids = new ArrayList<>();

		TbContentCategory selectByMap1 = content.selectById(categoryId);
		if (selectByMap1.getIsParent()) {

			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("parent_id", categoryId);
			List<TbContentCategory> selectByMap = content.selectByMap(columnMap);
			for (TbContentCategory content : selectByMap) {
				if (content.getIsParent()) {
					ids.addAll(getListCategoryId(content.getId()));
				} else {
					ids.add(content.getId());
				}

			}
		} else {
			ids.add(selectByMap1.getId());
		}
		return ids;
	}
}
