package ba.sitandfit.korisnici;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SitAndFitKorisniciApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitAndFitKorisniciApplication.class, args);
	}
}
