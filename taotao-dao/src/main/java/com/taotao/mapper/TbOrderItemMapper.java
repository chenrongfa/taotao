package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbOrderItem;

public interface TbOrderItemMapper extends BaseMapper<TbOrderItem> {
    int deleteByPrimaryKey(String id);

 

    int insertSelective(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderItem record);

    int updateByPrimaryKey(TbOrderItem record);
}