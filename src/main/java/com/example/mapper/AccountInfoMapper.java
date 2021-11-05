package com.example.mapper;

import com.example.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountInfo record);

    AccountInfo selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountInfo record);


}
