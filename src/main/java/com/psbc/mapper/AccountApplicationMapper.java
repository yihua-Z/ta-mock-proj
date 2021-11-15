package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;

public interface AccountApplicationMapper {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);

    AccountApplication selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}