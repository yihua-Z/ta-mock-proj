package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;
import com.psbc.pojo.AccountExpectationKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountExpectationDao {
    int deleteByPrimaryKey(AccountExpectationKey key);

    int insert(AccountExpectation record);

    int insertSelective(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(AccountExpectationKey key);

    int updateByPrimaryKeySelective(AccountExpectation record);

    int updateByPrimaryKey(AccountExpectation record);
}