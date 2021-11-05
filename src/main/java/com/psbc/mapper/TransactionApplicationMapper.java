package com.psbc.mapper;

import com.psbc.pojo.TransactionApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransactionApplication record);

    TransactionApplication selectByPrimaryKey(String id);

    int updateByPrimaryKey(TransactionApplication record);
}
