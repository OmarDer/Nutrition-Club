package ba.sitandfit.korisnici.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.repository.KorisnikRepository;

@Component
public class RoleServiceHelper {
	
	@Autowired
	private KorisnikRepository kr;
	
	public RoleServiceHelper() {
	}
	
	public Korisnik findByUserName(String username)
	{
		return kr.findByUserName(username);
	}
}
