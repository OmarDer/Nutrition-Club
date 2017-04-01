package ba.sitandfit.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SitAndFitConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitAndFitConfigServerApplication.class, args);
	}
}
