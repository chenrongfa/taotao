package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbItemDesc;

public interface TbItemDescMapper extends BaseMapper<TbItemDesc>{
    int deleteByPrimaryKey(Long itemId);

    

    int insertSelective(TbItemDesc record);

    TbItemDesc selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}