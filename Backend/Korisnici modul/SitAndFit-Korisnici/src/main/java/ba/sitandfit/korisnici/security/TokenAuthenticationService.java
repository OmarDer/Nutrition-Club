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
import ba.sitandfit.korisnici.service.KorisnikServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TokenAuthenticationService {
	
		static final long EXPIRATIONTIME = 864_000_000; // 10 dana, mjera je u ms!
		static final String SECRET = "FitAndSitTajna";
		static final String TOKEN_PREFIX = "Bearer";
		static final String HEADER_STRING = "Authorization";
		
		
public static void addAuthentication(HttpServletResponse res, String username,String rola
		) throws IOException {
		List<String> role= new ArrayList<String>();
		role.add(rola);
		
    	String JWT = Jwts.builder()
    			.setSubject(username).claim("authorities", role)
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
    			.signWith(SignatureAlgorithm.HS512, SECRET)
    			.compact();
    	res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    	res.getWriter().write("\"token\":\"Tokenback\"");
 }

public static Authentication getAuthentication(HttpServletRequest request) {
    	
    	String token = request.getHeader(HEADER_STRING);
    	if (token != null) {
    		String user = Jwts.parser()
    				.setSigningKey(SECRET)
    				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    				.getBody()
    				.getSubject();
    	
    		String rola= Jwts.parser()
    				.setSigningKey(SECRET)
    				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    				.getBody()
    				.get("authorities").toString();
    		
    		Collection<GrantedAuthority> prava = new HashSet<GrantedAuthority>();
    		//Zbog toga sto postoji samo jedna rola za korisnika i njen naziv se nalazi izmedju uglastih zagrada koje treba otkloniti
    		rola=rola.substring(1, rola.length()-1);
    		prava.add(new SimpleGrantedAuthority(rola));
    	
    		return new UsernamePasswordAuthenticationToken(user, null, prava);
    	}
    	return null;
  }
}