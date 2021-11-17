package com.psbc.mapper;

import com.psbc.pojo.AccountInfo;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountInfoDao {
    int deleteByPrimaryKey(String transactionaccountid);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(String transactionaccountid);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}