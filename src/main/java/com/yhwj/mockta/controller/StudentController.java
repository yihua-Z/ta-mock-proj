package com.yhwj.mockta.controller;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhwj.mockta.po.Student;
import com.yhwj.mockta.service.StudentService;

@RestController
public class StudentController {
	@Autowired 
	private StudentService studentService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(path = {"/helloworld"})
    public String helloworld(){
		logger.info("receive a request for helloworld");
        return "hello world";
       // return "hello spring boot";
    }
	
	@RequestMapping(path = {"/add"})
    public void addStudent(){
		logger.info("receive a request for add student");
        studentService.addStudent("weiqi", 30);
       // return "hello spring boot";
    }
    @RequestMapping(path = {"/query/{id}"})
    public Student queryStudent (@PathVariable("id") int studentId){
        return studentService.getStudentById(studentId);
    }
    @RequestMapping(path = {"/delete/{id}"})
    public String deleteStudent (@PathVariable("id") int studentId){
       studentService.deleteStudent(studentId);
      return MessageFormat.format("deleted {0}", studentId);
    }
}
