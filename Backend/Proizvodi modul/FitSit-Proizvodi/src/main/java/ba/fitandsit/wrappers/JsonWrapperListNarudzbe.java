package ba.fitandsit.wrappers;

import java.util.List;

import ba.fitandsit.model.*;

public class JsonWrapperListNarudzbe {
	
	private String status;
	private String poruka;
	private List<Narudzbe> narudzbe;
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
	public List<Narudzbe> getNarudzbe() {
		return narudzbe;
	}
	public void setNarudzbe(List<Narudzbe> narudzbe) {
		this.narudzbe = narudzbe;
	}
	public JsonWrapperListNarudzbe(String status,String poruka)
	{
		this.status=status;
		this.poruka=poruka;
	}
	public JsonWrapperListNarudzbe(String status,String poruka, List<Narudzbe> narudzbe)
	{
		this.status=status;
		this.poruka=poruka;
		this.narudzbe=narudzbe;
	}
	
}
