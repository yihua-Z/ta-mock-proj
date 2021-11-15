package com.psbc.mapper;

import com.psbc.pojo.AcctReconciliation;

public interface AcctReconciliationMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AcctReconciliation record);

    int insertSelective(AcctReconciliation record);

    AcctReconciliation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AcctReconciliation record);

    int updateByPrimaryKey(AcctReconciliation record);
}