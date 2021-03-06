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
import org.springframework.security.web.header.writers.StaticHeadersWriter;
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
		

		
		
		//http
		 //	.csrf().disable().authorizeRequests()
		
		/*http.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
					  .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"))
					  .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE"))
					  .addHeaderWriter(new StaticHeadersWriter("Access-Control-Expose-Headers", "Authorization"))
					  .and()*/
			http.csrf().disable().authorizeRequests()
//				.antMatchers(HttpMethod.OPTIONS, "/login").permitAll()
     			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
     			//.antMatchers(HttpMethod.GET, "/korisnici/odobri/**").hasAuthority("ROLE_ADMIN")
     			.antMatchers(HttpMethod.PUT, "/korisnici/**").permitAll()
     			.antMatchers(HttpMethod.GET, "/korisnici/**").permitAll()
     			.antMatchers(HttpMethod.POST,"/korisnici/registriraj").permitAll()
     			.antMatchers(HttpMethod.GET,"/submit/potvrdi/**").permitAll()
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