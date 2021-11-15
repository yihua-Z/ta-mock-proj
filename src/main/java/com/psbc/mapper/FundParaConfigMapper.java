package com.psbc.mapper;

import com.psbc.pojo.FundParaConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundParaConfigMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(FundParaConfig record);

    int insertSelective(FundParaConfig record);

    FundParaConfig selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(FundParaConfig record);

    int updateByPrimaryKey(FundParaConfig record);
}