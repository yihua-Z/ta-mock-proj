package com.psbc.mapper;

import com.psbc.pojo.TransactionApplication;
import com.psbc.pojo.TransactionApplicationKey;

public interface TransactionApplicationDao {
    int deleteByPrimaryKey(TransactionApplicationKey key);

    int insert(TransactionApplication record);

    int insertSelective(TransactionApplication record);

    TransactionApplication selectByPrimaryKey(TransactionApplicationKey key);

    int updateByPrimaryKeySelective(TransactionApplication record);

    int updateByPrimaryKey(TransactionApplication record);
}