package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbContentCategory;

public interface TbContentCategoryMapper extends BaseMapper<TbContentCategory> {
    int deleteByPrimaryKey(Long id);

   

    int insertSelective(TbContentCategory record);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);
}