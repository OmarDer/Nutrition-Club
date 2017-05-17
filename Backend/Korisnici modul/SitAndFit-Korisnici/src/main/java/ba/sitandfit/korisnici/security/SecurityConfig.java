package ba.sitandfit.korisnici.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userService;
	
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		AntPathMatcher ant= new AntPathMatcher();
		String pattern=ant.combine("/korisnici", "{id}");
		
		http
		 	.csrf().disable().authorizeRequests()
		 		.antMatchers(pattern).hasAnyAuthority("ROLE_ADMIN","ROLE_COMMUNICATION")
		 		.antMatchers("/korisnici/**").hasAuthority("ROLE_ADMIN")
		 		.antMatchers("/stanjakorisnika").hasAnyAuthority("ROLE_ADMIN","ROLE_PRODAVAC")
		 		.antMatchers("/").permitAll()
		 		.antMatchers(HttpMethod.POST, "/login").permitAll()
		 		.anyRequest().authenticated()
		 		
	        .and()
	        
	        	.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	        			UsernamePasswordAuthenticationFilter.class)
	    
	        	.addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	        	
	        	
		
	}
}