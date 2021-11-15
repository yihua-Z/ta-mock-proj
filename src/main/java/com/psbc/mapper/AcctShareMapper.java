package com.psbc.mapper;


import com.psbc.pojo.AcctShare;

public interface AcctShareMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AcctShare record);

    int insertSelective(AcctShare record);

    AcctShare selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AcctShare record);

    int updateByPrimaryKey(AcctShare record);
}