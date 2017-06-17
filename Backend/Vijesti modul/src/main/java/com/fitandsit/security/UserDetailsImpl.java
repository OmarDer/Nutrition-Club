package com.fitandsit.security;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fitandsit.security.*;

public class UserDetailsImpl implements UserDetailsService{

	private JSONObject korisnik;

	@Autowired
	public UserDetailsImpl(JSONObject korisnik)
	{
		this.korisnik=korisnik;
	}
	
	public JSONObject vratiKorisnikaZaUsername(String username)
	{
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		return null;
	}
}
