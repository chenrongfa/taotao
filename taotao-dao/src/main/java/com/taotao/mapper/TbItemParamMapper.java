package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbItemParam;

public interface TbItemParamMapper extends BaseMapper<TbItemParam> {
    int deleteByPrimaryKey(Long id);

   

    int insertSelective(TbItemParam record);

    TbItemParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemParam record);

    int updateByPrimaryKeyWithBLOBs(TbItemParam record);

    int updateByPrimaryKey(TbItemParam record);
}