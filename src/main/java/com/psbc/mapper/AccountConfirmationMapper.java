package com.psbc.mapper;


import com.psbc.pojo.AccountConfirmation;

public interface AccountConfirmationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountConfirmation record);

    int insertSelective(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AccountConfirmation record);

    int updateByPrimaryKey(AccountConfirmation record);
}