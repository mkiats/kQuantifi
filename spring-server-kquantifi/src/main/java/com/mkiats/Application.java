package com.mkiats;

import com.mkiats.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private final PerformanceService performanceService;

	public Application(PerformanceService thePerformanceService) {
		this.performanceService = thePerformanceService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		performanceService.doExecute();
	}
}
