package ba.sitandfit.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SitAndFitEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitAndFitEurekaServerApplication.class, args);
	}
}
