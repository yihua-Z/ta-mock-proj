package com.example.mapper;

import com.example.pojo.AccountInfo;
import com.example.pojo.AcctReconciliation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AcctReconciliationMapper {
    int deleteByPrimaryKey(String id);

    int insert(AcctReconciliation record);

    AcctReconciliation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AcctReconciliation record);
}
