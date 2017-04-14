package com.fitandsit.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

import org.json.*;

public class discoveryService {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public discoveryService(){}
	
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public DiscoveryClient getDiscoveryClient() {
		return this.discoveryClient;
	}
	
	public String discoveryService(String appName) {
	    List<ServiceInstance> list = discoveryClient.getInstances(appName);
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}

	public String getUsersServiceUrl()
	{
		String url=discoveryService("KORISNICI");
		return url;
	}
}
