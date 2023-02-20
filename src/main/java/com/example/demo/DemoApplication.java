package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import com.mysql.cj.exceptions.CJCommunicationsException;
import org.springframework.retry.annotation.EnableRetry;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableRetry
@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Retryable(value = {CJCommunicationsException.class,SocketTimeoutException.class}, maxAttempts = 3, backoff = @Backoff(delay = 100))
	public static void main(String[] args) {
		logger.info("start demo...");
		SpringApplication.run(DemoApplication.class, args);
	}

}
