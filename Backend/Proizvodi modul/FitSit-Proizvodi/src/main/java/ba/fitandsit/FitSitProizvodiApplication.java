package ba.fitandsit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class FitSitProizvodiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitSitProizvodiApplication.class, args);
	}
}
