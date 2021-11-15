package com.psbc.mapper;


import com.psbc.pojo.AccountInfo;

public interface AccountInfoMapper {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}