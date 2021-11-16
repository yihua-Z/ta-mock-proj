package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountApplicationKey;

import java.util.List;

public interface AccountApplicationDao {
    int deleteByPrimaryKey(AccountApplicationKey key);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);

    AccountApplication selectByPrimaryKey(AccountApplicationKey key);
    List<AccountApplication> selectAll() ;

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}