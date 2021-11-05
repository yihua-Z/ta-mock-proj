package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountExpectationMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountExpectation record);

}
