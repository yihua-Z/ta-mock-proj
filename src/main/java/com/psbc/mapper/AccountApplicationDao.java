package com.psbc.mapper;

import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountApplicationKey;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.DatabaseModel;
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

    List<AccountApplication> selectUnionPrimaryKey(AccountApplication record);


    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}