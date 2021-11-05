package com.psbc.mapper;

import com.psbc.pojo.AcctReconciliation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AcctReconciliationMapper {
    int deleteByPrimaryKey(String id);

    int insert(AcctReconciliation record);

    AcctReconciliation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AcctReconciliation record);
}
