package com.yhwj.mockta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhwj.mockta.dal.StudentMapper;
import com.yhwj.mockta.po.Student;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentDAL;

	public void addStudent(String name, int age){
		Student s  = new Student();
		//s.setId(1L);
		s.setName(name);
		s.setAge(age);
		
		studentDAL.insert(s);
	}
	
	public Student getStudentById(int id){
		return studentDAL.selectByPrimaryKey((long)id);
	}
	public void deleteStudent(int id){
		studentDAL.deleteByPrimaryKey((long)id);
	     
	}
}
