package com.psbc.mapper;

import com.psbc.pojo.AccountExpectation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountExpectationMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(String id);

    List<AccountExpectation> selectAll();
    int updateByPrimaryKey(AccountExpectation record);

}
