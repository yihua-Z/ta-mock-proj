package com.psbc.mapper;

import com.psbc.pojo.AccountConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountConfirmationDao {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountConfirmation record);

    int insertSelective(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(Integer confirmid);

    int updateByPrimaryKeySelective(AccountConfirmation record);

    int updateByPrimaryKey(AccountConfirmation record);
}