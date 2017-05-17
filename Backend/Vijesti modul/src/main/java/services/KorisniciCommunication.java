package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fitandsit.discovery.discoveryService;

import jsonwrappers.KomentariJSONWrapper;
import jsonwrappers.VijestiJSONWrapper;

import org.json.*;
@Service
public class KorisniciCommunication {
	
	@Autowired
	private komentariService ks;
	
	@Autowired
	private VijestiService vs;
	
	@Autowired
	private discoveryService ds;
	
	private static final String communicationUsername = "Komunikacija";
	private static final String communicationPassword = "Mikroservis";
	
	private String communicationToken(){
		RestTemplate rt = new RestTemplate();
		String url = ds.getUsersServiceUrl();
		
		String body = "{\"username\":\""+communicationUsername+"\",\"password\":\""+communicationPassword+"\"}";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity=new HttpEntity(body,headers);
		
		String konekcija=url+"/login";
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.POST, entity, String.class);
		
		return response.getHeaders().get("Authorization").get(0);
	}
	
	private String getKorisnikRemote(String url, long id){
		RestTemplate rt=new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		String tokenHeader=communicationToken();
		
		header.set("Authorization", tokenHeader);
		HttpEntity entity=new HttpEntity(header);
		String konekcija=url+id;
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.GET,entity,String.class);
		String korisnik=response.getBody();
		return korisnik;
	}
	
	private String getKorisnik(String url, Long id){
		RestTemplate rt=new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		String tokenHeader=communicationToken();
		
		header.set("Authorization", tokenHeader);
		HttpEntity entity=new HttpEntity(header);
		String konekcija=url+id;
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.GET,entity,String.class);
		String korisnik=response.getBody();
		return korisnik;
	}
	
	public String getAutorVijest(Long id)
	{
		RestTemplate rt=new RestTemplate();
		
		VijestiJSONWrapper k=vs.getvijesti(id);
		if (k.getStatus()=="Error")
		{
			String msg="{\"status\": \""+k.getStatus()+"\", \"poruka\":\""+k.getMsg()+"\", \"Vijest\": null}";
			return msg;
		}
		String url=ds.getUsersServiceUrl();
		if(k.getvijest().getAutorID()==null)
		{
			String msg="{\"status\": \"Error\", \"poruka\":\"Vijest nema nijednog autora!\", \"Vijest\": null}";
			return msg;
		}
		String korisnik = getKorisnik(url+"/korisnici/",k.getvijest().getAutorID());
		return korisnik;
	}
	
	public JSONObject getKorisnikByID(Long id)
	{
		RestTemplate rt=new RestTemplate();
		
		String url=ds.getUsersServiceUrl();
		String korisnik=getKorisnik(url+"/korisnici/",id);
		JSONObject jsonKorisnik;
		try {
				jsonKorisnik= new JSONObject(korisnik);
				if(checkKorisnikMetods(jsonKorisnik)==false) return null;
				return jsonKorisnik;
				
		} catch (JSONException e) {
			
			return null;
		}

	}
	
	
	//Metoda namjenjena za provjeru u klasi KomentariService/vijestiService pri kreiranju novog Komentara/vijesti, azuriranju, itd.
	public Boolean checkKorisnik(Long id)
	{
		
		RestTemplate rt=new RestTemplate();
		String url=ds.getUsersServiceUrl();
		String korisnik=getKorisnik(url+"/korisnici/",id);
		
		JSONObject jsonKorisnik;
		try {
			
			jsonKorisnik = new JSONObject(korisnik);
			if(getStatusMetod(jsonKorisnik).equals("Error"))
			{
				return false;
			}
			return true;
			
		} catch (JSONException e) {
			
			return false;
		}
		
	}
	
	public String getStatus(Long id)
	{
		RestTemplate rt=new RestTemplate();
		String url=ds.getUsersServiceUrl();
		String korisnik=getKorisnik(url+"/korisnici/",id);
		try {
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			return getStatusMetod(jsonKorisnik);
		} catch (JSONException e) {
			String msg="{\"status\": \"Error\", \"msg\":\"Podaci su neispravni!\", \"komentar/vijest\": null}";
			return msg;
		}
		
	}
	
	public String getMsg(Long id)
	{
		RestTemplate rt=new RestTemplate();
		String url=ds.getUsersServiceUrl();
		String korisnik=getKorisnik(url+"/korisnici/",id);
		try {
			JSONObject jsonKorisnik=new JSONObject(korisnik);
			return getMsgMetod(jsonKorisnik);
		} catch (JSONException e) {
			String msg="{\"status\": \"Error\", \"msg\":\"Podaci su neispravni!\", \"komentar/vijest\": null}";
			return msg;
		}
	}
	
	private Boolean checkKorisnikMetods(JSONObject jsonKorisnik)
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
	private String getStatusMetod(JSONObject json)
	{
		try {
			return json.getString("status");
		} catch (JSONException e) {
			return null;
		}
	}
	
	private String getMsgMetod(JSONObject json)
	{
		try {
			return json.getString("poruka");
		} catch (JSONException e) {
			return null;
		}
	}
	
	//Proslijediti ID korisnika iz vijesti
	public String getAutorKomentar(Long id)
	{
		KomentariJSONWrapper k=ks.getKomentar(id);
		RestTemplate rt=new RestTemplate();
		String url=ds.getUsersServiceUrl();
		//Ukoliko ne postoji Komentar sa datim id-jem
		if(k.getStatus().equals("Error"))
		{
			String msg="{\"status\": \""+k.getStatus()+"\", \"poruka\":\""+k.getMsg()+"\", \"Autor\": null}";
			return msg;
		}
		try {
			String autor=getKorisnik(url+"/korisnici/",k.getkomentar().getAutorID());
			JSONObject jsonKorisnik=new JSONObject(autor);
			if(getStatusMetod(jsonKorisnik).equals("Error"))
			{
				String msg="{\"status\": \""+getStatusMetod(jsonKorisnik)+"\", \"msg\":\""+getMsgMetod(jsonKorisnik)+"\", \"Autor\": null}";
				return msg;
			}
			return autor;
			
		} catch (JSONException e) {
			String msg="{\"status\": \"Error\", \"msg\":\"Podaci su neispravni!\", \"Autor\": null}";
			return msg;
		}
		
	}

}
