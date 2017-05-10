package ba.sitandfit.korisnici.security;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.repository.KorisnikRepository;
import ba.sitandfit.korisnici.service.KorisnikServiceImpl;

@Service
public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private KorisnikRepository korisnikRepo;

	@Autowired
	public UserDetailsImpl(KorisnikRepository korisnik)
	{
		this.korisnikRepo=korisnik;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Korisnik korisnik= korisnikRepo.findByUserName(username);
		if(korisnik==null)
		{
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsServiceImpl(korisnik);
	}
}
