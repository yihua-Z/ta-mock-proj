package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountApplicationMapper {


    int deleteByPrimaryKey(String id);

    int insert(AccountApplication record);

    AccountApplication selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountApplication record);

}
