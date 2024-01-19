package com.snipit.snipit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SnipitApplication {
	public static void main(String[] args) {

		SpringApplication.run(SnipitApplication.class, args);
	}

}
