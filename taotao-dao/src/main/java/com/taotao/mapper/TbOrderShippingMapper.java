package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbOrderShipping;

public interface TbOrderShippingMapper extends BaseMapper<TbOrderShipping> {
    int deleteByPrimaryKey(String orderId);

   

    int insertSelective(TbOrderShipping record);

    TbOrderShipping selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TbOrderShipping record);

    int updateByPrimaryKey(TbOrderShipping record);
}