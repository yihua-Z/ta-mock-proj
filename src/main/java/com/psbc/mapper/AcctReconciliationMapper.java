package com.psbc.mapper;

import com.psbc.pojo.AcctReconciliation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AcctReconciliationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AcctReconciliation record);

    int insertSelective(AcctReconciliation record);

    AcctReconciliation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AcctReconciliation record);

    int updateByPrimaryKey(AcctReconciliation record);
}