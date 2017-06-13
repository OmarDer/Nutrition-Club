package com.fitandsit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.fitandsit.Controller","com.fitandsit.jsonwrappers","com.fitandsit.services","com.fitandsit.security"})
@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan({"com.fitandsit.Controller","jsonwrappers","com.fitandsit.discovery","services.KorisniciCommunication","services","com.fitandsit.security"})
@EntityScan("com.fitandsit.Model")
@EnableJpaRepositories(basePackages ="com.fitandsit.Repository")
public class FitAndSitApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FitAndSitApplication.class, args);
	}
}
