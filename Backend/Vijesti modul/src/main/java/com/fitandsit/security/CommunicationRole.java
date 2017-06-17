package com.fitandsit.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
//@PropertySource("https://github.com/OmarDer/Nutrition-Club/Config/proizvodi-dev.properties")
public class CommunicationRole {

	//@Value("${my.korisnickoIme}")
    private String username;
 
   // @Value("${my.korisnickaLozinka}")
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
 
 
}
