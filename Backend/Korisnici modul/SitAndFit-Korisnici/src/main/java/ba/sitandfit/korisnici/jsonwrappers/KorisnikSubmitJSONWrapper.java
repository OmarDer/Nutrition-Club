package ba.sitandfit.korisnici.jsonwrappers;

import ba.sitandfit.korisnici.model.KorisnikSubmit;

public class KorisnikSubmitJSONWrapper {

	
	private String status;
	private String poruka;
	private KorisnikSubmit ks;
	
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

	public KorisnikSubmit getKs() {
		return ks;
	}

	public void setKs(KorisnikSubmit ks) {
		this.ks = ks;
	}

	public KorisnikSubmitJSONWrapper(){
		
	}
	
	public KorisnikSubmitJSONWrapper(String status,String poruka,KorisnikSubmit ks)
	{
		this.status=status;
		this.poruka=poruka;
		this.ks=ks;
	
	}
}
