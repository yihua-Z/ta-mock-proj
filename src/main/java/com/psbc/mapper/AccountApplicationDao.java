package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountApplicationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);

    AccountApplication selectByPrimaryKey(String appsheetserialno);
    List<AccountApplication> selectAll();
    List<AccountApplication> selectUnionPrimaryKey(AccountApplication record);

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}