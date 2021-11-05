package com.psbc.mapper;


import com.psbc.pojo.FundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FundInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FundInfo record);

    FundInfo selectByPrimaryKey(String id);

    int updateByPrimaryKey(FundInfo record);
}
