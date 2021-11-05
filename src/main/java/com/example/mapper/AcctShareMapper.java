package com.example.mapper;

import com.example.pojo.AccountInfo;
import com.example.pojo.AcctShare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AcctShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(AcctShare record);

    AcctShare selectByPrimaryKey(String id);

    int updateByPrimaryKey(AcctShare record);
}
