package com.minjun.gamerecommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GameRecommendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameRecommendApplication.class, args);
	}

}
