package com.yanghaoyi.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j2
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan({"com.yanghaoyi.redis","com.yanghaoyi.user","com.yanghaoyi.common","com.yanghaoyi.exception"})
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}


/**
 * API GETWAY
 * https://blog.csdn.net/shenzhen_zsw/article/details/80994318
 *
 * ***/
