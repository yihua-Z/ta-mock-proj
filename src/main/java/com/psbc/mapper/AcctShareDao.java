package com.psbc.mapper;

import com.psbc.pojo.AcctShare;

public interface AcctShareDao {
    int deleteByPrimaryKey(String taaccountid);

    int insert(AcctShare record);

    int insertSelective(AcctShare record);

    AcctShare selectByPrimaryKey(String taaccountid);

    int updateByPrimaryKeySelective(AcctShare record);

    int updateByPrimaryKey(AcctShare record);
}