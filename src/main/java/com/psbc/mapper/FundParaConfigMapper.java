package com.psbc.mapper;


import com.psbc.pojo.FundParaConfig;

public interface FundParaConfigMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(FundParaConfig record);

    int insertSelective(FundParaConfig record);

    FundParaConfig selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(FundParaConfig record);

    int updateByPrimaryKey(FundParaConfig record);
}