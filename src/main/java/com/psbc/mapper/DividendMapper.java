package com.psbc.mapper;

import com.psbc.pojo.Dividend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DividendMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(Dividend record);

    int insertSelective(Dividend record);

    Dividend selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(Dividend record);

    int updateByPrimaryKey(Dividend record);
}