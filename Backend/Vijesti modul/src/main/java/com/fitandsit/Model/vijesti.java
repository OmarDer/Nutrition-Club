package com.fitandsit.Model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public
 class vijesti {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long VijestID;
	
	private Long AutorID;

	private String nazivVijesti;

	private String textVijesti;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datum;
	
	@Autowired
	@ManyToOne
	private tipvijesti TipVijesti;
	
	@Autowired
	@ManyToOne
	private kategorijavijesti KategorijaVijesti;
	
	private Integer aktivan;

	public vijesti(){
	}
	
	public Long getVijestID(){
		return this.VijestID;
	}
	
	public void setVijestID(Long VijestID){
		this.VijestID = VijestID;
	}
	
	public Long getAutorID(){
		return this.AutorID;
	}
	
	public void setAutorID(Long AutorID){
		this.AutorID = AutorID;
	}

	public Integer getAktivan(){
		return this.aktivan;
	}
	public void setAktivan(Integer aktivan){
		this.aktivan = aktivan;
	}

	public String getNazivVijesti(){
		return this.nazivVijesti;
	}
	
	public void setNazivVijesti(String naziv){
		this.nazivVijesti = naziv;
	}
	
	public String getTextVijesti(){
		return this.textVijesti;
	}
	
	public void setTextvVijesti(String naziv){
		this.textVijesti = naziv;
	}
	
	public Date getDatum(){
		return this.datum;
	}
	
	public void setDatum(Date date){
		this.datum=date;
	}
	
	public void setTipVijesti(tipvijesti TipVijesti){
		this.TipVijesti = TipVijesti;
	}
	
	public tipvijesti getTipVijesti(){
		return this.TipVijesti;
	}
	
	public void setKategorijaVijesti(kategorijavijesti KategorijaVijesti){
		this.KategorijaVijesti = KategorijaVijesti;
	}
	
	public kategorijavijesti getKategorijaVijesti(){
		return this.KategorijaVijesti;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
