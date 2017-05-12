package ba.sitandfit.korisnici.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
//import org.hibernate.mapping.Collection;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Collection;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.repository.*;
import ba.sitandfit.korisnici.service.KorisnikService;
import ba.sitandfit.korisnici.service.KorisnikServiceImpl;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
public JWTLoginFilter(String url, AuthenticationManager authManager) 
  {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
    
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)throws AuthenticationException, IOException, ServletException {
	
	LoginData logdata = new ObjectMapper().readValue(req.getInputStream(), LoginData.class);
	
    return getAuthenticationManager().authenticate( new UsernamePasswordAuthenticationToken(
    		logdata.getUsername(),
    		logdata.getPassword(),
    		Collections.emptyList()   
        )
    );
  }

 @Override
  protected void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
	  
	TokenAuthenticationService.addAuthentication(res, auth.getName(),Iterables.get(auth.getAuthorities(),0).getAuthority().toString());
  }
}