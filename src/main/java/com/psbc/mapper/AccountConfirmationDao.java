package com.psbc.mapper;

import com.psbc.pojo.AccountConfirmation;

public interface AccountConfirmationDao {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountConfirmation record);

    int insertSelective(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AccountConfirmation record);

    int updateByPrimaryKey(AccountConfirmation record);
}