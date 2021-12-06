package com.psbc.mapper;

import com.psbc.pojo.TransactionApplication;
import com.psbc.pojo.TransactionApplicationKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransactionApplicationDao {
    int deleteByPrimaryKey(TransactionApplicationKey key);

    int insert(TransactionApplication record);

    int insertSelective(TransactionApplication record);

    TransactionApplication selectByPrimaryKey(TransactionApplicationKey key);

    List<TransactionApplication> selectAll();

    int updateByPrimaryKeySelective(TransactionApplication record);

    int updateByPrimaryKey(TransactionApplication record);
}