package ba.fitandsit.servicediscovery;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

import org.json.*;

@Service
public class ServiceUrl {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public ServiceUrl(){}
	
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public DiscoveryClient getDiscoveryClient() {
		return this.discoveryClient;
	}
	
	public String serviceUrl(String appName) {
	    List<ServiceInstance> list = discoveryClient.getInstances(appName);
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}

	public String getUsersServiceUrl()
	{
		String url=serviceUrl("KORISNICI");
		return url;
	}
}
