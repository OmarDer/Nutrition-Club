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
		
		/*http
			.authorizeRequests()
				.antMatchers("/korisnici").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.usernameParameter("userName")
				.and()
			.logout();
		*/
		
		http
		 	.csrf().disable().authorizeRequests()
		 		.antMatchers("/korisnici/").hasRole("ADMIN")
		 		.antMatchers("/stanjakorisnika/").hasAnyRole("ADMIN","PRODAVAC")
		 		.antMatchers("/").permitAll()
		 		.antMatchers(HttpMethod.POST, "/login").permitAll()
		 		.anyRequest().authenticated()
		 		
	        .and()
	        
	        	.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	        			UsernamePasswordAuthenticationFilter.class)
	    
	        	.addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
}