package com.psbc.mapper;

import com.psbc.pojo.TaProperty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface TaPropertyDao {
    int deleteByPrimaryKey(String TACode);

    int insert(TaProperty record);

    int insertSelective(TaProperty record);

    TaProperty selectByPrimaryKey(String TACode);

    int updateByPrimaryKeySelective(TaProperty record);

    int updateByPrimaryKey(TaProperty record);
}