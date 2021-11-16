package com.psbc.mapper;

import com.psbc.pojo.FundInfo;

public interface FundInfoDao {
    int deleteByPrimaryKey(String fundcode);

    int insert(FundInfo record);

    int insertSelective(FundInfo record);

    FundInfo selectByPrimaryKey(String fundcode);

    int updateByPrimaryKeySelective(FundInfo record);

    int updateByPrimaryKey(FundInfo record);
}