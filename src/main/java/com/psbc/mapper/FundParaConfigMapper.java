package com.psbc.mapper;

import com.psbc.pojo.FundParaConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundParaConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(FundParaConfig record);

    FundParaConfig selectByPrimaryKey(String id);

    int updateByPrimaryKey(FundParaConfig record);
}
