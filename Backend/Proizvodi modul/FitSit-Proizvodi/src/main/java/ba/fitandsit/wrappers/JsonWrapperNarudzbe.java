package ba.fitandsit.wrappers;

import ba.fitandsit.model.*;
public class JsonWrapperNarudzbe {
	
	private String status;
	 
	private String poruka;
	
	private Narudzbe narudzba;

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

	public Narudzbe getNarudzba() {
		return narudzba;
	}

	public void setNarudzba(Narudzbe narudzba) {
		this.narudzba = narudzba;
	}
	
	public JsonWrapperNarudzbe(String status, String poruka, Narudzbe narudzba)
	{
		super();
		this.status=status;
		this.poruka=poruka;
		this.narudzba=narudzba;
	}
	
	
	

}
