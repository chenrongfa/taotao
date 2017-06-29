package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbItemParamItem;

public interface TbItemParamItemMapper extends BaseMapper<TbItemParamItem> {
    int deleteByPrimaryKey(Long id);



    int insertSelective(TbItemParamItem record);

    TbItemParamItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(TbItemParamItem record);

    int updateByPrimaryKey(TbItemParamItem record);
}