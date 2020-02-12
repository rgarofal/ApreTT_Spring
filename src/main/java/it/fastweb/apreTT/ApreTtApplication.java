package it.fastweb.apreTT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
public class ApreTtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApreTtApplication.class, args);
	}

}
