package com.psbc.mapper;

import com.psbc.pojo.TransactionExpectation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionExpectationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(TransactionExpectation record);

    int insertSelective(TransactionExpectation record);

    TransactionExpectation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(TransactionExpectation record);

    int updateByPrimaryKey(TransactionExpectation record);
}