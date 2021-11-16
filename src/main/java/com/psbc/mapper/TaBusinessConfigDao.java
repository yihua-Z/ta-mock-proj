package com.psbc.mapper;

import com.psbc.pojo.TaBusinessConfig;

public interface TaBusinessConfigDao {
    int deleteByPrimaryKey(String tacode);

    int insert(TaBusinessConfig record);

    int insertSelective(TaBusinessConfig record);

    TaBusinessConfig selectByPrimaryKey(String tacode);

    int updateByPrimaryKeySelective(TaBusinessConfig record);

    int updateByPrimaryKey(TaBusinessConfig record);
}