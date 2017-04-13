package ba.fitandsit.wrappers;

import java.util.List;

import ba.fitandsit.model.*;

public class JsonWrapperListProizvodi {
	
	private String status;
	private String poruka;
	private List<Proizvodi> proizvodi;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public List<Proizvodi> getProizvodi() {
		return proizvodi;
	}
	public void setProizvodi(List<Proizvodi> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	public JsonWrapperListProizvodi(String status,String poruka)
	{
		this.status=status;
		this.poruka=poruka;
		
	}
	public JsonWrapperListProizvodi(String status,String poruka, List<Proizvodi> proizvodi)
	{
		this.status=status;
		this.poruka=poruka;
		this.proizvodi=proizvodi;
	}
}
