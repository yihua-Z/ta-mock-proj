package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountApplicationMapper {


    int insert(AccountApplication record);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKey(AccountApplication record);

    AccountApplication selectByPrimaryKey(String id);


}
