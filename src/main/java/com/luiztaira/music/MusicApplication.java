package com.luiztaira.music;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableRabbit
public class MusicApplication{

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}
}