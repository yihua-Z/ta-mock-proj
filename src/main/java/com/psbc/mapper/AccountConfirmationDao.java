package com.psbc.mapper;

import com.psbc.pojo.AccountConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountConfirmationDao {
    int deleteByPrimaryKey(Integer confirmid);

    int insert(AccountConfirmation record);

    int insertSelective(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(Integer confirmid);

    List<AccountConfirmation> selectAll();

    int updateByPrimaryKeySelective(AccountConfirmation record);

    int updateByPrimaryKey(AccountConfirmation record);
}