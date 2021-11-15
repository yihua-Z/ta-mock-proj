package com.psbc.mapper;


import com.psbc.pojo.FundInfo;

public interface FundInfoMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(FundInfo record);

    int insertSelective(FundInfo record);

    FundInfo selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(FundInfo record);

    int updateByPrimaryKey(FundInfo record);
}