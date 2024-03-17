package com.bits.ml_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bits.ml_tool", "com.bits.ml_tool.controller" })
public class MlToolApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MlToolApplication.class, args);
	}

}
