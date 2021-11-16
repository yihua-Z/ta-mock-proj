package com.psbc.mapper;

import com.psbc.pojo.FundDate;
import com.psbc.pojo.FundDateKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundDateDao {
    int deleteByPrimaryKey(FundDateKey key);

    int insert(FundDate record);

    int insertSelective(FundDate record);

    FundDate selectByPrimaryKey(FundDateKey key);

    int updateByPrimaryKeySelective(FundDate record);

    int updateByPrimaryKey(FundDate record);
}