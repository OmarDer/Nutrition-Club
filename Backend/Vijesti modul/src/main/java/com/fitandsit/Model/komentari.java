package com.fitandsit.Model;

import javax.persistence.*;

@Entity
public class komentari {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long KomentarID;
	
	private String textKomentara;
	
	@ManyToOne
	private vijesti Vijest;
	
	private Long AutorID;
	
	private Integer ImaRoditelja;
	
	@ManyToOne
	private komentari komentar;
	
	private Integer aktivan;

	public komentari(){
	}
	
	public Long getKomentarID(){
		return this.KomentarID;
	}
	
	public void setKomentarID(Long KomentarID){
		this.KomentarID = KomentarID;
	}
	
	public Long getAutorID(){
		return this.AutorID;
	}
	
	public void setAutorID(Long AutorID){
		this.AutorID = AutorID;
	}
	
	public komentari getRoditelj(){
		return this.komentar;
	}
	
	public void setkomentar(komentari komentar){
		this.komentar = komentar;
	}
	
	public vijesti getVijesti(){
		return this.Vijest;
	}
	
	public void setVijesti(vijesti vijest){
		this.Vijest = vijest;
	}

	public Integer getAktivan(){
		return this.aktivan;
	}
	public void setAktivan(Integer aktivan){
		this.aktivan = aktivan;
	}

	public Integer getImaRoditelja(){
		return this.ImaRoditelja;
	}
	
	public void setImaRoditelja(Integer ImaRoditelja){
		this.ImaRoditelja = ImaRoditelja;
	}
	
	public String getTextKomentara(){
		return this.textKomentara;
	}
	public void setTextKomentara(String textKomentara){
		this.textKomentara = textKomentara;
	}
	//

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
