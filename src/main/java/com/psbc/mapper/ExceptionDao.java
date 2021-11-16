package com.psbc.mapper;

import com.psbc.pojo.Exception;
import com.psbc.pojo.ExceptionKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExceptionDao {
    int deleteByPrimaryKey(ExceptionKey key);

    int insert(Exception record);

    int insertSelective(Exception record);

    Exception selectByPrimaryKey(ExceptionKey key);

    int updateByPrimaryKeySelective(Exception record);

    int updateByPrimaryKey(Exception record);
}