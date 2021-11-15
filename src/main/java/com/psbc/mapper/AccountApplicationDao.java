package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountApplicationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);

    AccountApplication selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}