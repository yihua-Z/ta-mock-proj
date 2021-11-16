package com.psbc.mapper;

import com.psbc.pojo.TransactionExpectation;

public interface TransactionExpectationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(TransactionExpectation record);

    int insertSelective(TransactionExpectation record);

    TransactionExpectation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(TransactionExpectation record);

    int updateByPrimaryKey(TransactionExpectation record);
}