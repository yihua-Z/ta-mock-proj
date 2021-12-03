package com.psbc.mapper;

import com.psbc.pojo.TransactionExpectation;
import com.psbc.pojo.TransactionExpectationKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionExpectationDao {
    int deleteByPrimaryKey(TransactionExpectationKey key);

    int insert(TransactionExpectation record);

    int insertSelective(TransactionExpectation record);

    TransactionExpectation selectByPrimaryKey(TransactionExpectation record);

    int updateByPrimaryKeySelective(TransactionExpectation record);

    int updateByPrimaryKey(TransactionExpectation record);
}