package com.psbc.mapper;

import com.psbc.pojo.FundDate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundDateMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(FundDate record);

    int insertSelective(FundDate record);

    FundDate selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(FundDate record);

    int updateByPrimaryKey(FundDate record);
}