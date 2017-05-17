package ba.sitandfit.korisnici.communication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProizvodiRESTImpl implements ProizvodiREST{

	private static final RestTemplate restTemplate = new RestTemplate();
	//private static final String appName = "PROIZVODI";
	//private static final String appNameGetToken = "KORISNICI";
	private static final String communicationUsername = "Komunikacija";
	private static final String communicationPassword = "Mikroservis";
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	private String serviceUrl(String appName) {
		
	    List<ServiceInstance> list = discoveryClient.getInstances(appName);
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	
	
	@Override
	public String getProgramiByKorisnikId(Long korisnikId) {
		
		String konekcija=serviceUrl("PROIZVODI");
		String communication=connectToProizvodi(konekcija+"/programi/korisnik/", korisnikId);
		return communication;
		//return restTemplate.getForObject(serviceUrl() +"/programi/korisnik/" + korisnikId, String.class);
	}
	
	@Override
	public String napraviTokenZaKomunikaciju()
	{
		String url = serviceUrl("KORISNICI");
			
		String body = "{\"username\":\""+communicationUsername+"\",\"password\":\""+communicationPassword+"\"}";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity=new HttpEntity(body,headers);
		
		String konekcija=url+"/login";
		
		ResponseEntity<String> response = restTemplate.exchange(konekcija, HttpMethod.POST, entity, String.class);
		
		return response.getHeaders().get("Authorization").get(0);
			
	}
	
	@Override
	public String connectToProizvodi(String url,Long id)
	{
		RestTemplate rt=new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String tokenHeader=napraviTokenZaKomunikaciju();
		
		header.set("Authorization", tokenHeader);
		HttpEntity entity=new HttpEntity(header);
		String konekcija=url+id;
		
		ResponseEntity<String> response = rt.exchange(konekcija, HttpMethod.GET,entity,String.class);
		String respBody=response.getBody();
		return respBody;
	}

}
