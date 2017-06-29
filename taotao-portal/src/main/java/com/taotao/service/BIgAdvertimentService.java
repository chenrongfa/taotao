/**
 * 
 */
package com.taotao.service;

import java.util.List;

import com.taotao.bean.BIgAdvertiment;

/**
 *description :TODO
 * createTime:2017年6月17日 下午12:34:07
 * project:ssm
 * @author chenrongfa 
 * QQ:952786280 
 * company:逸臣
 * @version:
 */
public interface BIgAdvertimentService {
	List<BIgAdvertiment> getBIgAdvertimentByHttpcliean(String url);

}
