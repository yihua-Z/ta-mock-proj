package com.psbc.mapper;

import com.psbc.pojo.TransactionConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionConfirmationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(TransactionConfirmation record);

    int insertSelective(TransactionConfirmation record);

    TransactionConfirmation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(TransactionConfirmation record);

    int updateByPrimaryKey(TransactionConfirmation record);
}