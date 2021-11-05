package com.psbc.mapper;

import com.psbc.pojo.AccountConfirmation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountConfirmationMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountConfirmation record);

}
