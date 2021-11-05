package com.example.mapper;

import com.example.pojo.AccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountApplicationMapper {


    int deleteByPrimaryKey(String id);

    int insert(AccountApplication record);

    AccountApplication selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountApplication record);

}
