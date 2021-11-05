package com.example.mapper;

import com.example.pojo.AccountExpectation;
import com.example.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountExpectationMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountExpectation record);

    AccountExpectation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountExpectation record);

}
