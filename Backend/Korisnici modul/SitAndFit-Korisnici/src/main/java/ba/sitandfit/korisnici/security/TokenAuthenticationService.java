package ba.sitandfit.korisnici.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.repository.KorisnikRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;

import static java.util.Collections.emptyList;

import java.util.Collection;

public class TokenAuthenticationService {
	
		static final long EXPIRATIONTIME = 864_000_000; // 10 dana, mjera je u ms!
		static final String SECRET = "FitAndSitTajna";
		static final String TOKEN_PREFIX = "Bearer";
		static final String HEADER_STRING = "Authorization";
		
		@Autowired
		static private KorisnikRepository korisnikRepo;
		
public static void addAuthentication(HttpServletResponse res, String username//,String rola
		) {
		
    	String JWT = Jwts.builder()
    			.setSubject(username)//.claim("role", rola)
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
    			.signWith(SignatureAlgorithm.HS512, SECRET)
    			.compact();
    	res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
 }

public static Authentication getAuthentication(HttpServletRequest request) {
    	
    	String token = request.getHeader(HEADER_STRING);
    	if (token != null) {
    		String user = Jwts.parser()
    				.setSigningKey(SECRET)
    				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    				.getBody()
    				.getSubject();
    		/*
    		String rola=(String) Jwts.parser()
    				.setSigningKey(SECRET)
    				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    				.getBody()
    				.get("role");
    		if (korisnikRepo.findByUserName(user)==null)return null;
    		
    		String role=korisnikRepo.findByUserName(user).getRola().getNazivRole();
    		Collection<GrantedAuthority> prava = new HashSet<GrantedAuthority>();
    		prava.add(new SimpleGrantedAuthority(role));
    		
    		new UsernamePasswordAuthenticationToken(user, null, prava);
    		*/
    		return user != null ?
    				new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
    				null;
    	}
    	return null;
  }
}