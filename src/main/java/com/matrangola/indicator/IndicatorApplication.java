package com.matrangola.indicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class IndicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndicatorApplication.class, args);
	}
}
