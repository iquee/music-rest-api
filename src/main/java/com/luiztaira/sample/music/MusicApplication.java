package com.luiztaira.sample.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.mongobee.Mongobee;

@SpringBootApplication
@EnableMongoRepositories
public class MusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Bean @Autowired
	public Mongobee mongobee(Environment env) {
		String host = "mongodb://localhost/music";
		String dockerUri = env.getProperty("spring.data.mongodb.uri");
		if(dockerUri != null){
			System.out.println("---------- Running on Docker ----------");
			host = dockerUri;
		} else{
			System.out.println("---------- Running local ----------");
		}
		Mongobee runner = new Mongobee(host);
		runner.setDbName("music");
		runner.setChangeLogsScanPackage("com.luiztaira.sample.music.changelog");

		return runner;
	}
}