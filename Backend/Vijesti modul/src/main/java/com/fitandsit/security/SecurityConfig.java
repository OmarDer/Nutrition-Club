package com.fitandsit.security;
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
import org.springframework.util.AntPathMatcher;

import com.fitandsit.security.JWTAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
//		AntPathMatcher ant= new AntPathMatcher();
//		String pattern=ant.combine("/programi/korisnik", "{id}");
		http
		//.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","*"))
				  //.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"))
		//.and()
	 	.csrf().disable().authorizeRequests()
	 		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	 		.antMatchers(HttpMethod.GET,"/vijesti").permitAll()
	 		.antMatchers(HttpMethod.GET,"/vijesti/**").permitAll()
	 		.antMatchers("/komentari/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/tipvijesti/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/kategorijevijesti/all").hasAuthority("ROLE_ADMIN")
	 		.antMatchers("/kategorijevijesti/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AUTOR","ROLE_PRODAVAC")
	 		.antMatchers("/komentari/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AUTOR","ROLE_PRODAVAC")
	 		.antMatchers("/tipvijesti/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AUTOR","ROLE_PRODAVAC")
	 		.antMatchers("/vijesti/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AUTOR","ROLE_PRODAVAC")
	 		.antMatchers("/").permitAll()
	 		.antMatchers(HttpMethod.POST, "/login").permitAll()
	 		.anyRequest().authenticated()
	 		
        .and()
        
        	.addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
}
