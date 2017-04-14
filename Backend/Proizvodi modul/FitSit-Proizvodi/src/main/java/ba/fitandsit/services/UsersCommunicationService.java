package ba.fitandsit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;
import ba.fitandsit.repository.*;
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
	
	public String dajKorisnikaZaProgram(Long id)
	{
		RestTemplate rt=new RestTemplate();
		
		JsonWrapperProgrami p=ps.vratiProgramPoID(id);
		if (p.getStatus()=="Error")
		{
			String poruka="{\"status\": \""+p.getStatus()+"\", \"poruka\":\""+p.getPoruka()+"\", \"program\": null}";
			return poruka;
		}
		String url=su.getUsersServiceUrl();
		if(p.getProgram().getAutorID()==null)
		{
			String poruka="{\"status\": \"Error\", \"poruka\":\"Program nema nijednog korisnika!\", \"program\": null}";
			return poruka;
		}
		return rt.getForObject(url+"/korisnici/"+p.getProgram().getAutorID(), String.class);
	}
	
	public JSONObject vratiKorisnikeZaID(Long id)
	{
		RestTemplate rt=new RestTemplate();
		
		String url=su.getUsersServiceUrl();
		String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
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
	public Boolean provjeriKorisnika(Long id)
	{
		
		RestTemplate rt=new RestTemplate();
		String url=su.getUsersServiceUrl();
		String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
		
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
		RestTemplate rt=new RestTemplate();
		String url=su.getUsersServiceUrl();
		String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
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
		RestTemplate rt=new RestTemplate();
		String url=su.getUsersServiceUrl();
		String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
		try {
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			return vratiPorukuZaMetode(jsonKorisnik);
		} catch (JSONException e) {
			String poruka="{\"status\": \"Error\", \"poruka\":\"Podaci su neispravni!\", \"program\": null}";
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
	
	//Proslijediti ID korisnika iz narudzbe
	public String vratiKupceZaNarudzbu(Long id)
	{
		JsonWrapperNarudzbe n=ns.vratiNarudzbuPoID(id);
		RestTemplate rt=new RestTemplate();
		String url=su.getUsersServiceUrl();
		//Ukoliko ne postoji narudzba sa datim id-jem
		if(n.getStatus().equals("Error"))
		{
			String poruka="{\"status\": \""+n.getStatus()+"\", \"poruka\":\""+n.getPoruka()+"\", \"korisnik\": null}";
			return poruka;
		}
		try {
			String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
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
	
	public String vratiProdavacaZaNarudzbu(Long id)
	{
		JsonWrapperNarudzbe n=ns.vratiNarudzbuPoID(id);
		RestTemplate rt=new RestTemplate();
		String url=su.getUsersServiceUrl();
		//Ukoliko ne postoji narudzba sa datim id-jem
		if(n.getStatus().equals("Error"))
		{
			String poruka="{\"status\": \""+n.getStatus()+"\", \"poruka\":\""+n.getPoruka()+"\", \"korisnik\": null}";
			return poruka;
		}
		try {
			String korisnik=rt.getForObject(url+"/korisnici/"+id, String.class);
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
	
}
