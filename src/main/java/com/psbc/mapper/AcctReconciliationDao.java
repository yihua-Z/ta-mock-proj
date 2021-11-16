package com.psbc.mapper;

import com.psbc.pojo.AcctReconciliation;

public interface AcctReconciliationDao {
    int deleteByPrimaryKey(Integer reconciliationid);

    int insert(AcctReconciliation record);

    int insertSelective(AcctReconciliation record);

    AcctReconciliation selectByPrimaryKey(Integer reconciliationid);

    int updateByPrimaryKeySelective(AcctReconciliation record);

    int updateByPrimaryKey(AcctReconciliation record);
}