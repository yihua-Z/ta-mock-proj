package com.psbc.mapper;

import com.psbc.pojo.FundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundInfoMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(FundInfo record);

    int insertSelective(FundInfo record);

    FundInfo selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(FundInfo record);

    int updateByPrimaryKey(FundInfo record);
}