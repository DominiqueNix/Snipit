package com.snipit.snipit;


import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnipitApplication {
	public static ArrayList<SnipitModel> snipets = new ArrayList<>();
	public static void main(String[] args) {

		SpringApplication.run(SnipitApplication.class, args);
	}

}
