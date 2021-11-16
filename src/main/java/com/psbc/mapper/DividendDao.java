package com.psbc.mapper;

import com.psbc.pojo.Dividend;

public interface DividendDao {
    int deleteByPrimaryKey(Integer dividendid);

    int insert(Dividend record);

    int insertSelective(Dividend record);

    Dividend selectByPrimaryKey(Integer dividendid);

    int updateByPrimaryKeySelective(Dividend record);

    int updateByPrimaryKey(Dividend record);
}