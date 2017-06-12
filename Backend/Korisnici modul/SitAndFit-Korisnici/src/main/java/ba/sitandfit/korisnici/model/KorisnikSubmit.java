package ba.sitandfit.korisnici.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="korisniciSubmit")
public class KorisnikSubmit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SUBMIT_ID")
	private Long id;
	
	@Column(name="KORISNIK_ID")
	private Long korisnikId;
	
	@Column(name="GENERATED_STRING")
	private String generatedString;
	
	@Column(name="POTVRDJEN")
	private int potvrdjen;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getGeneratedString() {
		return generatedString;
	}

	public void setGeneratedString(String generatedString) {
		this.generatedString = generatedString;
	}

	public int getPotvrdjen() {
		return potvrdjen;
	}

	public void setPotvrdjen(int odobren) {
		this.potvrdjen = odobren;
	}

	public KorisnikSubmit(String gen,Long korisnik){
		this.korisnikId=korisnik;
		this.generatedString=gen;
		this.potvrdjen=0;
	}
	
	public KorisnikSubmit(){}

}
