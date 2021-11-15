package com.psbc.mapper;

import com.psbc.pojo.FundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundInfoDao {
    int deleteByPrimaryKey(String fundcode);

    int insert(FundInfo record);

    int insertSelective(FundInfo record);

    FundInfo selectByPrimaryKey(String fundcode);

    int updateByPrimaryKeySelective(FundInfo record);

    int updateByPrimaryKey(FundInfo record);
}