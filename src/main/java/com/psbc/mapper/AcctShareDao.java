package com.psbc.mapper;

import com.psbc.pojo.AcctShare;
import com.psbc.pojo.AcctShareKey;

public interface AcctShareDao {
    int deleteByPrimaryKey(AcctShareKey key);

    int insert(AcctShare record);

    int insertSelective(AcctShare record);

    AcctShare selectByPrimaryKey(AcctShareKey key);

    int updateByPrimaryKeySelective(AcctShare record);

    int updateByPrimaryKey(AcctShare record);
}