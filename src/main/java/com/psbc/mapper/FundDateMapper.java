package com.psbc.mapper;

import com.psbc.pojo.FundDate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundDateMapper {
    int deleteByPrimaryKey(String id);

    int insert(FundDate record);

    FundDate selectByPrimaryKey(String id);

    int updateByPrimaryKey(FundDate record);
}
