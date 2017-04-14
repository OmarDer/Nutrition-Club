package com.fitandsit.Model;

import javax.persistence.*;

@Entity
public class kategorijavijesti {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long KategorijaID;

	private String imeKategorije;

	private Integer aktivan;

	public kategorijavijesti(){
	}
	
	public Integer getAktivan(){
		return this.aktivan;
	}
	public void setAktivan(Integer aktivan){
		this.aktivan = aktivan;
	}
	
	public Long getKategorijaID(){
		return this.KategorijaID;
	}
	public void setKategorijaID(Long KategorijaID){
		this.KategorijaID = KategorijaID;
	}
	
	public String getImeKategorije(){
		return this.imeKategorije;
	}
	public void setNazivTipa(String imeKategorije){
		this.imeKategorije = imeKategorije;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
