package ba.fitandsit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.*;
import ba.fitandsit.repository.*;
import ba.fitandsit.security.LoginData;
import ba.fitandsit.servicediscovery.*;
import ba.fitandsit.wrappers.*;

@Service
public class UsersCommunicationService {
	
	@Autowired
	private ProgramiRepository pr;
	
	@Autowired
	private ProgramiService ps;
	
	@Autowired
	private NarudzbeService ns;
	
	@Autowired
	private ServiceUrl su;
	
	private static final String communicationUsername = "Komunikacija";
	private static final String communicationPassword = "Mikroservis";
	
	private String napraviTokenZaKomunikaciju()
	{
		RestTemplate rt=new RestTemplate();
		String url = su.getUsersServiceUrl();
			
		String body = "{\"username\":\""+communicationUsername+"\",\"password\":\""+communicationPassword+"\"}";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity=new HttpEntity(body,headers);
		
		String konekcija=url+"/login";
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.POST, entity, String.class);
		
		return response.getHeaders().get("Authorization").get(0);
			
	}
	
	private String nadjiKorisnika(String url,Long id)
	{
		RestTemplate rt=new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		String tokenHeader=napraviTokenZaKomunikaciju();
		
		header.set("Authorization", tokenHeader);
		HttpEntity entity=new HttpEntity(header);
		String konekcija=url+id;
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.GET,entity,String.class);
		String korisnik=response.getBody();
		return korisnik;
	}
	
	
	public String dajKorisnikaZaProgram(Long id, String token)
	{	
		JsonWrapperProgrami p=ps.vratiProgramPoID(id);
		if (p.getStatus()=="Error")
		{
			String poruka="{\"status\": \""+p.getStatus()+"\", \"poruka\":\""+p.getPoruka()+"\", \"program\": null}";
			return poruka;
		}
		
		if(p.getProgram().getAutorID()==null)
		{
			String poruka="{\"status\": \"Error\", \"poruka\":\"Program nema nijednog korisnika!\", \"program\": null}";
			return poruka;
		}
		//Dodan authorization header
		String url=su.getUsersServiceUrl();
		String korisnik = nadjiKorisnika(url+"/korisnici/",p.getProgram().getAutorID());

		return korisnik;
	}
	
	public JSONObject vratiKorisnikeZaID(Long id)
	{
			
		String url=su.getUsersServiceUrl();
		String korisnik=nadjiKorisnika(url+"/korisnici/",id);
		JSONObject jsonKorisnik;
		try {
				jsonKorisnik= new JSONObject(korisnik);
				if(provjeriKorisnikaZaMetode(jsonKorisnik)==false) return null;
				return jsonKorisnik;
				
		} catch (JSONException e) {
			
			return null;
		}

	}
	
	
	//Metoda namjenjena za provjeru u klasi ProgramiService pri kreiranju novog programa, azuriranju, itd.
	public Boolean provjeriKorisnika(Long id, String token)
	{
		String url=su.getUsersServiceUrl();
		String korisnik=nadjiKorisnika(url+"/korisnici/",id);
		JSONObject jsonKorisnik;
		try {
			
			jsonKorisnik = new JSONObject(korisnik);
			if(vratiStatusZaMetode(jsonKorisnik).equals("Error"))
			{
				return false;
			}
			return true;
			
		} catch (JSONException e) {
			
			return false;
		}
		
	}
	
	public String vratiStatus(Long id)
	{
		String url=su.getUsersServiceUrl();
		String korisnik=nadjiKorisnika(url+"/korisnici/",id);
		try {
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			return vratiStatusZaMetode(jsonKorisnik);
		} catch (JSONException e) {
			String poruka="{\"status\": \"Error\", \"poruka\":\"Podaci su neispravni!\", \"program\": null}";
			return poruka;
		}
		
	}
	
	public String vratiPoruku(Long id)
	{
		String url=su.getUsersServiceUrl();
		String korisnik=nadjiKorisnika(url+"/korisnici/",id);	
		try {
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			return vratiPorukuZaMetode(jsonKorisnik);
		} catch (JSONException e) {
			String poruka="{\"status\": \"Error\", \"poruka\":\"Podaci su neispravni!\", \"program\": null}";
			return poruka;
		}
	}
	
	
	public String vratiKupceZaNarudzbu(Long id, String token)
	{
		JsonWrapperNarudzbe n=ns.vratiNarudzbuPoID(id);
		String url=su.getUsersServiceUrl();
		
		//Ukoliko ne postoji narudzba sa datim id-jem
		if(n.getStatus().equals("Error"))
		{
			String poruka="{\"status\": \""+n.getStatus()+"\", \"poruka\":\""+n.getPoruka()+"\", \"korisnik\": null}";
			return poruka;
		}
		try {
			
			String korisnik=nadjiKorisnika(url+"/korisnici/",id);
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			if(vratiStatusZaMetode(jsonKorisnik).equals("Error"))
			{
				String poruka="{\"status\": \""+vratiStatusZaMetode(jsonKorisnik)+"\", \"poruka\":\""+vratiPorukuZaMetode(jsonKorisnik)+"\", \"korisnik\": null}";
				return poruka;
			}
			return korisnik;
			
		} catch (JSONException e) {
			String poruka="{\"status\": \"Error\", \"poruka\":\"Podaci su neispravni!\", \"korisnik\": null}";
			return poruka;
		}
		
	}
	
	public String vratiProdavacaZaNarudzbu(Long id, String token)
	{
		JsonWrapperNarudzbe n=ns.vratiNarudzbuPoID(id);
		String url=su.getUsersServiceUrl();
		
		//Ukoliko ne postoji narudzba sa datim id-jem
		if(n.getStatus().equals("Error"))
		{
			String poruka="{\"status\": \""+n.getStatus()+"\", \"poruka\":\""+n.getPoruka()+"\", \"korisnik\": null}";
			return poruka;
		}
		try {
			
			String korisnik=nadjiKorisnika(url+"/korisnici/",id);
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			
			if(vratiStatusZaMetode(jsonKorisnik).equals("Error"))
			{
				String poruka="{\"status\": \""+vratiStatusZaMetode(jsonKorisnik)+"\", \"poruka\":\""+vratiPorukuZaMetode(jsonKorisnik)+"\", \"korisnik\": null}";
				return poruka;
			}
			return korisnik;
			
		} catch (JSONException e) {
			String poruka="{\"status\": \"Error\", \"poruka\":\"Podaci su neispravni!\", \"korisnik\": null}";
			return poruka;
		}
	}
	
	private Boolean provjeriKorisnikaZaMetode(JSONObject jsonKorisnik)
	{
		
		try {
			if(jsonKorisnik.getString("status").equals("Error"))
			{
				return false;
			}
			return true;
			
		} catch (JSONException e) {
			
			return false;
		}
		
	}
	private String vratiStatusZaMetode(JSONObject json)
	{
		try {
			return json.getString("status");
		} catch (JSONException e) {
			return null;
		}
	}
	
	private String vratiPorukuZaMetode(JSONObject json)
	{
		try {
			return json.getString("poruka");
		} catch (JSONException e) {
			return null;
		}
	}
	
	
	
}
