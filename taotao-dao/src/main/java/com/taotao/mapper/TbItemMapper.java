package com.taotao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbItem;

public interface TbItemMapper extends BaseMapper<TbItem> {
    int deleteByPrimaryKey(long id);

 

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
    List<TbItem> selectAll();
}