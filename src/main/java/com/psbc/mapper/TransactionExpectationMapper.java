package com.psbc.mapper;

import com.psbc.pojo.TransactionExpectation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionExpectationMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransactionExpectation record);

    TransactionExpectation selectByPrimaryKey(String id);

    int updateByPrimaryKey(TransactionExpectation record);
}
