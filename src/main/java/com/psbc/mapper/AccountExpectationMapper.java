package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountExpectationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountExpectation record);

    int insertSelective(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AccountExpectation record);

    int updateByPrimaryKey(AccountExpectation record);
}