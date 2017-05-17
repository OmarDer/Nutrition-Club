package ba.fitandsit.security;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import io.jsonwebtoken.Jwts;

public class TokenAuthenticationService {
	
	//static final long EXPIRATIONTIME = 864_000_000; // 10 dana, mjera je u ms!
	static final String SECRET = "FitAndSitTajna";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
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
