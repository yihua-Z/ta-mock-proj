package com.psbc.mapper;

import com.psbc.pojo.AccountConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountConfirmationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountConfirmation record);

    int insertSelective(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AccountConfirmation record);

    int updateByPrimaryKey(AccountConfirmation record);
}