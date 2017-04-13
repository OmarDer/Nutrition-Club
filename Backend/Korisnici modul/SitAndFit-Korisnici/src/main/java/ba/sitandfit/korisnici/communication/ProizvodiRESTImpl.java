package ba.sitandfit.korisnici.communication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProizvodiRESTImpl implements ProizvodiREST{

	private static final RestTemplate restTemplate = new RestTemplate();
	private static final String appName = "PROIZVODI";
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	private String serviceUrl() {
		
	    List<ServiceInstance> list = discoveryClient.getInstances(appName);
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	
	
	@Override
	public String getProgramiByKorisnikId(Long korisnikId) {
		
		return restTemplate.getForObject(serviceUrl() +"/programi/korisnik/" + korisnikId, String.class);
	}

}
