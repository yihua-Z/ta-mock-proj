package com.psbc.mapper;

import com.psbc.pojo.AcctShare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AcctShareDao {
    int deleteByPrimaryKey(String taaccountid);

    int insert(AcctShare record);

    int insertSelective(AcctShare record);

    AcctShare selectByPrimaryKey(String taaccountid);

    int updateByPrimaryKeySelective(AcctShare record);

    int updateByPrimaryKey(AcctShare record);
}