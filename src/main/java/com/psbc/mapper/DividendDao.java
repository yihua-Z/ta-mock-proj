package com.psbc.mapper;

import com.psbc.pojo.Dividend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DividendDao {
    int deleteByPrimaryKey(Integer dividendid);

    int insert(Dividend record);

    int insertSelective(Dividend record);

    Dividend selectByPrimaryKey(Integer dividendid);

    int updateByPrimaryKeySelective(Dividend record);

    int updateByPrimaryKey(Dividend record);
}