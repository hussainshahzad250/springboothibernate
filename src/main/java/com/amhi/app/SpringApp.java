package com.amhi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.amhi.*"})
//@ComponentScan(basePackages="com.amhi.*")
//@EnableAutoConfiguration
//@Configuration
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
	}
}
