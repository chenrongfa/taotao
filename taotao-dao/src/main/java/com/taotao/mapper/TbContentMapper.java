package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbContent;

public interface TbContentMapper extends BaseMapper<TbContent> {
    int deleteByPrimaryKey(Long id);

 

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);
}