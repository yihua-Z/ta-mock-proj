package com.psbc.mapper;

import com.psbc.pojo.Holiday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface HolidayDao {
    int deleteByPrimaryKey(String tacode);

    int insert(Holiday record);

    int insertSelective(Holiday record);

    Holiday selectByPrimaryKey(String tacode);

    List<Holiday> selectByCondition(Holiday record);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
}