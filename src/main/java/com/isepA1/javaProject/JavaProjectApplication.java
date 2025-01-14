package com.isepA1.javaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration(proxyBeanMethods=false)
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

}
