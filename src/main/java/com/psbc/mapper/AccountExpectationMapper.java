package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;

import java.util.List;


public interface AccountExpectationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountExpectation record);

    int insertSelective(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(Integer confirmid);

    List<AccountExpectation> selectAll();

    int updateByPrimaryKeySelective(AccountExpectation record);

    int updateByPrimaryKey(AccountExpectation record);
}