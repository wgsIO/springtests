package com.github.walkgs.springtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
