package com.psbc.mapper;

import com.psbc.pojo.PrepaymentAcct;

public interface PrepaymentAcctDao {
    int deleteByPrimaryKey(String tacode);

    int insert(PrepaymentAcct record);

    int insertSelective(PrepaymentAcct record);

    PrepaymentAcct selectByPrimaryKey(String tacode);

    int updateByPrimaryKeySelective(PrepaymentAcct record);

    int updateByPrimaryKey(PrepaymentAcct record);
}