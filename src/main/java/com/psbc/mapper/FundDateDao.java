package com.psbc.mapper;

import com.psbc.pojo.FundDate;
import com.psbc.pojo.FundDateKey;

public interface FundDateDao {
    int deleteByPrimaryKey(FundDateKey key);

    int insert(FundDate record);

    int insertSelective(FundDate record);

    FundDate selectByPrimaryKey(FundDateKey key);

    int updateByPrimaryKeySelective(FundDate record);

    int updateByPrimaryKey(FundDate record);
}