package com.example.mapper;

import com.example.pojo.AccountConfirmation;
import com.example.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountConfirmationMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountConfirmation record);

    AccountConfirmation selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountConfirmation record);

}
