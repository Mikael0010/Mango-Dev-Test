package com.mmede.MangoDevTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MangoDevTestApplication {

	private static final Logger log = LoggerFactory.getLogger(MangoDevTestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MangoDevTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository, ItemRepository repository2) {
	  return (args) -> {
		// save a few users
		try{
			repository.save(new User("abc", "123"));
			repository.save(new User("cba", "321"));
			repository2.save(new Item("Groceries", "Apples and oranges"));
		}catch(Exception e){

		}
		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (User user : repository.findAll()) {
		  log.info(user.toString());
		}
		log.info("");
	  };
	}
}
