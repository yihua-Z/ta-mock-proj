package com.yhwj.mockta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yhwj.mockta.service.ConfigService;

@SpringBootApplication
@MapperScan(basePackages={"com.alan.webtest.dal"})
public class MockTaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockTaSpringApplication.class, args);
		
		//测试配置
		System.out.println("测试读取配置：" + ConfigService.getInstance().getProperty("test.ding"));
	}

}
