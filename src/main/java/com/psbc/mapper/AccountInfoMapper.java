package com.psbc.mapper;

import com.psbc.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccountInfo record);

    AccountInfo selectByPrimaryKey(String id);

    int updateByPrimaryKey(AccountInfo record);


}
