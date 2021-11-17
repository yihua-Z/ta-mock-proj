package com.psbc.mapper;

import com.psbc.pojo.TaPropertyConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaPropertyConfigDao {
    int deleteByPrimaryKey(String tacode);

    int insert(TaPropertyConfig record);

    int insertSelective(TaPropertyConfig record);

    TaPropertyConfig selectByPrimaryKey(String tacode);

    int updateByPrimaryKeySelective(TaPropertyConfig record);

    int updateByPrimaryKey(TaPropertyConfig record);
}