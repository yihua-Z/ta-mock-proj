package com.psbc.mapper;


import com.psbc.pojo.TransactionConfirmation;

public interface TransactionConfirmationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(TransactionConfirmation record);

    int insertSelective(TransactionConfirmation record);

    TransactionConfirmation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(TransactionConfirmation record);

    int updateByPrimaryKey(TransactionConfirmation record);
}