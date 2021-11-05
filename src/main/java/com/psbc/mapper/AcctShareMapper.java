package com.psbc.mapper;

import com.psbc.pojo.AcctShare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AcctShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(AcctShare record);

    AcctShare selectByPrimaryKey(String id);

    int updateByPrimaryKey(AcctShare record);
}
