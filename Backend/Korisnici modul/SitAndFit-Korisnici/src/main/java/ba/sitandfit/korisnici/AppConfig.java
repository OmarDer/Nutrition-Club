package ba.sitandfit.korisnici;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ba.sitandfit.korisnici.repository")
public class AppConfig {

}
