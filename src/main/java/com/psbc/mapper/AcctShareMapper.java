package com.psbc.mapper;

import com.psbc.pojo.AcctShare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AcctShareMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AcctShare record);

    int insertSelective(AcctShare record);

    AcctShare selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AcctShare record);

    int updateByPrimaryKey(AcctShare record);
}