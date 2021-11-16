package com.psbc.mapper;

import com.psbc.pojo.TimedApplication;

public interface TimedApplicationDao {
    int deleteByPrimaryKey(Integer appsheetserialno);

    int insert(TimedApplication record);

    int insertSelective(TimedApplication record);

    TimedApplication selectByPrimaryKey(Integer appsheetserialno);

    int updateByPrimaryKeySelective(TimedApplication record);

    int updateByPrimaryKey(TimedApplication record);
}