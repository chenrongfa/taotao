package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbOrder;

public interface TbOrderMapper extends BaseMapper<TbOrder> {
    int deleteByPrimaryKey(String orderId);

  

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);
}