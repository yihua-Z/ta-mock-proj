package com.yhwj.mockta.dal;

import org.apache.ibatis.annotations.Mapper;

import com.yhwj.mockta.po.Student;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}