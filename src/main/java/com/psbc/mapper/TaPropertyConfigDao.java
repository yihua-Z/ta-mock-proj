package com.psbc.mapper;

import com.psbc.pojo.TaPropertyConfig;

public interface TaPropertyConfigDao {
    int insert(TaPropertyConfig record);

    int insertSelective(TaPropertyConfig record);
}