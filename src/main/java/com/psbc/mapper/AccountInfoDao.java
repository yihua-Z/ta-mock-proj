package com.psbc.mapper;

import com.psbc.pojo.AccountInfo;

public interface AccountInfoDao {
    int deleteByPrimaryKey(Integer transactionaccountid);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer transactionaccountid);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}