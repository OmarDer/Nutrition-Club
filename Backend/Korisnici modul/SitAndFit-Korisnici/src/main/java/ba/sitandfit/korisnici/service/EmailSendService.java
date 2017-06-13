package ba.sitandfit.korisnici.service;

import com.google.common.collect.Lists;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.KorisnikSubmit;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class EmailSendService {
	
	 @Autowired
	private EmailService emailService;
	 
	 @Autowired
	private KorisnikSubmitService ksr;
	 
	
	 @Autowired 
	private KorisnikService ks;
	
	    public void sendEmail(Long id) throws UnsupportedEncodingException {
	    	
	    	KorisnikJSONWrapper korisnik= ks.getKorisnik(id);
	    	if(korisnik.getStatus() == "Success")
	    	{
	    		String validMailAdress = korisnik.getKorisnik().getEmail();
	    		String generatedString = ksr.getKorisnikSubmitByUserId(id).getKs().getGeneratedString();
	    		String name = korisnik.getKorisnik().getIme();
	    		String surname=korisnik.getKorisnik().getPrezime();
	    		
	    		String link="http://localhost/Frontend/#!/verification/";
	    		
	    		String emailBody = "Poštovani korisniče, vaš mail je verificiran i potvrdom na adresu " + link + generatedString + " vaš račun će biti aktivan.";
	    	
	    	List<InternetAddress> internetAddresses=new ArrayList<InternetAddress>();
	    	internetAddresses.add(new InternetAddress(validMailAdress,name+" "+surname));
	    	
	        final Email email = DefaultEmail.builder()
	                .from(new InternetAddress("sitandfit24@gmail.com", "Udruzenje Sit&Fit"))
	                .to(internetAddresses)
	                .subject("Verifikacija korisničkog računa")
	                .body(emailBody)
	                .encoding("UTF-8").build();

	        emailService.send(email);
	        
	    	}
	   }


}
