package com.psbc.mapper;

import com.psbc.pojo.FundParaConfig;

public interface FundParaConfigDao {
    int deleteByPrimaryKey(Integer fundparaid);

    int insert(FundParaConfig record);

    int insertSelective(FundParaConfig record);

    FundParaConfig selectByPrimaryKey(Integer fundparaid);

    int updateByPrimaryKeySelective(FundParaConfig record);

    int updateByPrimaryKey(FundParaConfig record);
}