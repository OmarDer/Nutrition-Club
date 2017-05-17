package ba.fitandsit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import ba.fitandsit.security.JWTAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		AntPathMatcher ant= new AntPathMatcher();
		String pattern=ant.combine("/programi/korisnik", "{id}");
		http
	 	.csrf().disable().authorizeRequests()
	 		.antMatchers(pattern).hasAnyAuthority("ROLE_ADMIN","ROLE_COMMUNICATION")
	 		.antMatchers("/programi/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/narudzbe/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/proizvodi/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/programi/**").hasAnyAuthority("ROLE_ADMIN","ROLE_PRODAVAC")
	 		.antMatchers("/proizvodi/**").hasAnyAuthority("ROLE_ADMIN","ROLE_PRODAVAC")
	 		.antMatchers("/narudzbe/**").hasAnyAuthority("ROLE_ADMIN","ROLE_PRODAVAC")
	 		.antMatchers("/").permitAll()
	 		.antMatchers(HttpMethod.POST, "/login").permitAll()
	 		.anyRequest().authenticated()
	 		
        .and()
        
        	.addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
}
