package com.example;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@EnableJms
public class RegApplication {



	public static void main(String[] args) {

		// Clean out any ActiveMQ data from a previous run
		FileSystemUtils.deleteRecursively(new File("activemq-data"));
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(RegApplication.class, args);


	}


}
