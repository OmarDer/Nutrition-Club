package ba.sitandfit.korisnici.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
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

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private KorisnikRepository kr;
	
	
	@Autowired
	public KorisnikRepository getKr() {
		return kr;
	}

	@Autowired
	public void setKr(KorisnikRepository kr) {
		this.kr = kr;
	}
	

public JWTLoginFilter(String url, AuthenticationManager authManager) 
  {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)throws AuthenticationException, IOException, ServletException {
	//RoleServiceHelper kr=new RoleServiceHelper();
	
	LoginData logdata = new ObjectMapper().readValue(req.getInputStream(), LoginData.class);
	/*
	Korisnik k=kr.findByUserName(logdata.getUsername());
	if (k!=null)
	{
		String rola=k.getRola().getNazivRole();
		Collection<GrantedAuthority> prava = new HashSet<GrantedAuthority>();
		prava.add(new SimpleGrantedAuthority(rola));
		
		return getAuthenticationManager().authenticate( new UsernamePasswordAuthenticationToken(
	    		logdata.getUsername(),
	    		logdata.getPassword(),
	    		prava  
	        )
	    );
		
	}	
	*/
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
	  /*
	  //RoleServiceHelper kr=new RoleServiceHelper();
	  Korisnik k=kr.findByUserName(auth.getName());
	  String rola=k.getRola().getNazivRole();
	  TokenAuthenticationService.addAuthentication(res, auth.getName(),rola);
	  */
	  TokenAuthenticationService.addAuthentication(res, auth.getName());
  }
}