package com.taotao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.taotao.bean.TbUser;

public interface TbUserMapper extends BaseMapper<TbUser>{
    int deleteByPrimaryKey(Long id);

    

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}