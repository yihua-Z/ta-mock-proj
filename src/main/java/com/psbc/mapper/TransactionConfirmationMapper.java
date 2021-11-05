package com.psbc.mapper;

import com.psbc.pojo.TransactionConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionConfirmationMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransactionConfirmation record);

    TransactionConfirmation selectByPrimaryKey(String id);

    int updateByPrimaryKey(TransactionConfirmation record);
}
