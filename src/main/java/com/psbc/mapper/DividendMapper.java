package com.psbc.mapper;


import com.psbc.pojo.Dividend;

public interface DividendMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(Dividend record);

    int insertSelective(Dividend record);

    Dividend selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(Dividend record);

    int updateByPrimaryKey(Dividend record);
}