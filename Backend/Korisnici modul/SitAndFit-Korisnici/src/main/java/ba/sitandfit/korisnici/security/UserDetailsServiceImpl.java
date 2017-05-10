package ba.sitandfit.korisnici.security;

import java.util.Collection;
import java.util.HashSet;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ba.sitandfit.korisnici.jsonwrappers.*;
import ba.sitandfit.korisnici.model.*;

public class UserDetailsServiceImpl implements UserDetails{

	@Autowired
	private Korisnik korisnik;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> prava = new HashSet<GrantedAuthority>();
		Rola rola= korisnik.getRola();
		prava.add(new SimpleGrantedAuthority(rola.getNazivRole()));
		return prava;
	}

	public UserDetailsServiceImpl(Korisnik korisnik)
	{
		this.korisnik=korisnik;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return korisnik.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return korisnik.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}