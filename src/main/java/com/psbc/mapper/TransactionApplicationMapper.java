package com.psbc.mapper;


import com.psbc.pojo.TransactionApplication;

public interface TransactionApplicationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(TransactionApplication record);

    int insertSelective(TransactionApplication record);

    TransactionApplication selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(TransactionApplication record);

    int updateByPrimaryKey(TransactionApplication record);
}