package com.fitandsit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.fitandsit.Controller","com.fitandsit.jsonwrappers","com.fitandsit.services"})
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.fitandsit.Controller","jsonwrappers","services"})
@EntityScan("com.fitandsit.Model")
@EnableJpaRepositories(basePackages ="com.fitandsit.Repository")
public class FitAndSitApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FitAndSitApplication.class, args);
	}
}
