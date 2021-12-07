package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountApplicationKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountApplicationDao {
    int deleteByPrimaryKey(AccountApplicationKey key);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);
    List<AccountApplication> selectAll();
    AccountApplication selectByPrimaryKey(AccountApplication key);

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}