package com.mkiats;

import com.mkiats.services.PerformanceService;
import com.mkiats.services.RetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private final PerformanceService performanceService;

	public Application(PerformanceService thePerformanceService){
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
