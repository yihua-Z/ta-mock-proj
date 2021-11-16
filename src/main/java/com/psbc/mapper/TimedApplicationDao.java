package com.psbc.mapper;

import com.psbc.pojo.TimedApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TimedApplicationDao {
    int deleteByPrimaryKey(Integer appsheetserialno);

    int insert(TimedApplication record);

    int insertSelective(TimedApplication record);

    TimedApplication selectByPrimaryKey(Integer appsheetserialno);

    int updateByPrimaryKeySelective(TimedApplication record);

    int updateByPrimaryKey(TimedApplication record);
}