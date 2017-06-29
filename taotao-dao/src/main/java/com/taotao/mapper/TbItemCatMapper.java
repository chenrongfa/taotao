package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbItemCat;

public interface TbItemCatMapper extends BaseMapper<TbItemCat> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(TbItemCat record);

    TbItemCat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);
}