package com.psbc.mapper;


import com.psbc.pojo.TransactionExpectation;

public interface TransactionExpectationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(TransactionExpectation record);

    int insertSelective(TransactionExpectation record);

    TransactionExpectation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(TransactionExpectation record);

    int updateByPrimaryKey(TransactionExpectation record);
}