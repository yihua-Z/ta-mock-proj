package com.psbc.mapper;

import com.psbc.pojo.TimedTask;

public interface TimedTaskDao {
    int deleteByPrimaryKey(Integer taskid);

    int insert(TimedTask record);

    int insertSelective(TimedTask record);

    TimedTask selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(TimedTask record);

    int updateByPrimaryKey(TimedTask record);
}