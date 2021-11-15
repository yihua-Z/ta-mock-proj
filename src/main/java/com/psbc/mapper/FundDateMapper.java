package com.psbc.mapper;


import com.psbc.pojo.FundDate;

public interface FundDateMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(FundDate record);

    int insertSelective(FundDate record);

    FundDate selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(FundDate record);

    int updateByPrimaryKey(FundDate record);
}