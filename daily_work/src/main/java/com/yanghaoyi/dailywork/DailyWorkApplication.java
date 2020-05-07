package com.yanghaoyi.dailywork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DailyWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyWorkApplication.class, args);
	}

}
