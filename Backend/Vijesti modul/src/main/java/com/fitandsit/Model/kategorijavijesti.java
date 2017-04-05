package com.fitandsit.Model;

import javax.persistence.*;

@Entity
public class kategorijavijesti {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long KategorijaID;

	private String imeKategorije;

	private Integer Arhiviran;

	public kategorijavijesti(){
	}
	
	public Integer getArhiviran(){
		return this.Arhiviran;
	}
	public void setArhiviran(Integer arhiviran){
		this.Arhiviran = arhiviran;
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
