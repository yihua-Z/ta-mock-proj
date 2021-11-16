package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;

import java.util.List;

public interface AccountExpectationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountExpectation record);

    int insertSelective(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(String appsheetserialno);

    List<AccountExpectation> selectAll();

    int updateByPrimaryKeySelective(AccountExpectation record);

    int updateByPrimaryKey(AccountExpectation record);
}