package com.psbc.mapper;

import com.psbc.pojo.FundParaConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundParaConfigDao {
    int deleteByPrimaryKey(Integer fundparaid);

    int insert(FundParaConfig record);

    int insertSelective(FundParaConfig record);

    FundParaConfig selectByPrimaryKey(Integer fundparaid);


    FundParaConfig selectByUnionCode(FundParaConfig record);

    int updateByPrimaryKeySelective(FundParaConfig record);

    int updateByPrimaryKey(FundParaConfig record);
}