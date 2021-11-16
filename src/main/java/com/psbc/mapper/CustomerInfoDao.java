package com.psbc.mapper;

import com.psbc.pojo.CustomerInfo;

public interface CustomerInfoDao {
    int deleteByPrimaryKey(Integer taaccountid);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer taaccountid);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);
}