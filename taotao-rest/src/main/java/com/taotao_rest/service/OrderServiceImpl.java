/**
 * 
 */
package com.taotao_rest.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.bean.Msg;
import com.taotao.bean.TbOrder;
import com.taotao.bean.TbOrderItem;
import com.taotao.bean.TbOrderShipping;
import com.taotao.bean.TbUser;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.utils.IdUtils;
import com.taotao.utils.JsonUtils;
import com.taotao_rest.bean.OrderCreate;
import com.taotao_rest.bean.OrderStatus;
import com.taotao_rest.bean.UserOrders;
import com.taotao_rest.dao.OrderCreateMapper;
import com.taotao_rest.dao.UserOrdersMapper;
import com.taotao_rest.utils.CookieUtils;

/**
 * @description :TODO
 * @since 2017年6月26日下午8:18:18
 * @see taotao-portal
 * @author chenrongfa
 * @QQ:952786280
 * @company:逸臣
 * @version:
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Value("${cookie_key}")
	private String cookie_key;
	@Value("${tbuser_cache_key}")
	private String cookie_prix;

	@Autowired
	private RedisService redisService;

	@Autowired
	private TbOrderMapper tborderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	private UserOrdersMapper userOrdersMapper;
	@Autowired
	private OrderCreateMapper orderCreateMapper;

	@Override
	public Msg createOrder(OrderCreate order, HttpServletRequest request) {
		Msg msg = new Msg();
		long userId = 0;
		TbOrder tborder = new TbOrder();
		try {

			if (order.getUserId() == null) {
				// 得到用户id
				System.out.println(112335 + "" + request.getRequestURL());
				String cookieValue = CookieUtils.getCookieValue(request, cookie_key, "utf-8");
				if (redisService != null) {
					TbUser user = (TbUser) redisService.getBeanValue(cookie_prix + cookieValue, TbUser.class);
					userId = user.getId();
					if (user.getUsername() != null)
						tborder.setBuyerNick(user.getUsername());
					else {
						tborder.setBuyerNick(order.getBuyerNick());
					}
				}
			} else {
				tborder.setUserId(order.getUserId());
			}
			// 创建订单对象
			long order_id = IdUtils.getinstance().makeItemId();
			tborder.setOrderId(order_id + "");
			tborder.setBuyerMessage(order.getBuyerMessage());
			tborder.setUserId(userId);
			tborder.setCreateTime(new Date());
			tborder.setPaymentType(order.getPaymentType());
			tborder.setPayment(order.getPayment());
			tborder.setPostFee(order.getPostFee());
			// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
			tborder.setStatus(1);
			// 插入 tborder对象
			tborderMapper.insert(tborder);
			// 创建order_items对象
			List<TbOrderItem> items = order.getOrderItems();
			for (TbOrderItem item : items) {
				item.setOrderId(order_id + "");
				item.setId(IdUtils.getinstance().makeItemId() + "");
				// item
				tbOrderItemMapper.insertSelective(item);
			}
			// 创建tborder_shipping对象
			TbOrderShipping shipping = order.getOrderShipping();
			shipping.setOrderId(order_id + "");
			shipping.setCreated(new Date());
			// 插入对象
			tbOrderShippingMapper.insertSelective(shipping);
			return msg.addSuccessMessage();
		} catch (Exception e) {
			// TODO: handle exception
			return msg.addErrorMessage();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taotao_rest.service.OrderService#updateOrderStatus(com.taotao_rest.
	 * bean.OrderStatus)
	 */
	@Override
	public Msg updateOrderStatus(OrderStatus status) {
		Msg msg=new Msg();
		try {
			TbOrder tbOrder = tborderMapper.selectById(status.getOrderId());
			if(tbOrder!=null){
				tbOrder.setStatus(status.getStatus());
				tbOrder.setPaymentTime(status.getPaymentTime());
				tborderMapper.updateByPrimaryKey(tbOrder);
				return msg.addSuccessMessage();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return msg.addErrorMessage();
		}
		return null;
	}

	
	@Override
	public Msg getUserOrderList(long userId, long page, long count) {
		Msg msg=new Msg();
		PageHelper.startPage((int)page, (int)count);
		Map<String, Object> columnMap=new HashMap<>();
		columnMap.put("user_id", userId);
		List<UserOrders> map = userOrdersMapper.selectByMap(columnMap);
		if(map!=null&&map.size()>0){
			msg.addSuccessMessage();
		msg.getDataBean().put("data", JsonUtils.objectToJson(map));
		return msg;
		}
		return msg.addErrorMessage();
	}

	@Override
	public Msg getOrderCreateByOderId(String userId) {
		Msg msg=new Msg();
		OrderCreate orderCreate = orderCreateMapper.selectByOrderId(userId);
		if(orderCreate!=null){
			msg.addSuccessMessage();
			msg.getDataBean().put("data", JsonUtils.objectToJson(orderCreate));
			return msg;
		}
		return msg.addErrorMessage();
	}
}
	

	


