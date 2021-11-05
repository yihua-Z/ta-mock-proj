package com.psbc.mapper;

import com.psbc.pojo.Dividend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DividendMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dividend record);

    Dividend selectByPrimaryKey(String id);

    int updateByPrimaryKey(Dividend record);
}
