package org.task.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main Application class which initialize the application
 * @author Manu Mohan
 *
 */
@ComponentScan(basePackages={"org.task.services"})
@SpringBootApplication
public class ServicesApplication {

	/**
	 * The entry point for the application
	 * @param args the Input aguments
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

}
