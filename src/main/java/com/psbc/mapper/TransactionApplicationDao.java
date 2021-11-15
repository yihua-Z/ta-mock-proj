package com.psbc.mapper;

import com.psbc.pojo.TransactionApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionApplicationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(TransactionApplication record);

    int insertSelective(TransactionApplication record);

    TransactionApplication selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(TransactionApplication record);

    int updateByPrimaryKey(TransactionApplication record);
}