package com.qhy.ppmsadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableOpenApi
public class PpmsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpmsAdminApplication.class, args);
	}

}
