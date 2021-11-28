package com.self.learning.playbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlaybookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaybookApplication.class, args);
	}

}
