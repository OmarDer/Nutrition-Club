package ba.sitandfit.korisnici.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "stanja_korisnika")
public class Stanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="STANJE_ID")
	private long id;
	private double tezina;
	private double procenatMasnoce;
	private double organskeMasnoce;
	private double postotakMisica;
	private double procenatVode;
	private double bazalniMetabolizam;
	private double kostanaMasa;
	private double metabolickeGodine;
	private int fizickoStanje;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datumAnalize;
	
	@ManyToOne
	@JoinColumn(name="KORISNIK_ID")
	@JsonManagedReference
	private Korisnik korisnik;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTezina() {
		return tezina;
	}

	public void setTezina(double tezina) {
		this.tezina = tezina;
	}

	public double getProcenatMasnoce() {
		return procenatMasnoce;
	}

	public void setProcenatMasnoce(double procenatMasnoce) {
		this.procenatMasnoce = procenatMasnoce;
	}

	public double getOrganskeMasnoce() {
		return organskeMasnoce;
	}

	public void setOrganskeMasnoce(double organskeMasnoce) {
		this.organskeMasnoce = organskeMasnoce;
	}

	public double getPostotakMisica() {
		return postotakMisica;
	}

	public void setPostotakMisica(double postotakMisica) {
		this.postotakMisica = postotakMisica;
	}

	public double getProcenatVode() {
		return procenatVode;
	}

	public void setProcenatVode(double procenatVode) {
		this.procenatVode = procenatVode;
	}

	public double getBazalniMetabolizam() {
		return bazalniMetabolizam;
	}

	public void setBazalniMetabolizam(double bazalniMetabolizam) {
		this.bazalniMetabolizam = bazalniMetabolizam;
	}

	public double getKostanaMasa() {
		return kostanaMasa;
	}

	public void setKostanaMasa(double kostanaMasa) {
		this.kostanaMasa = kostanaMasa;
	}

	public double getMetabolickeGodine() {
		return metabolickeGodine;
	}

	public void setMetabolickeGodine(double metabolickeGodine) {
		this.metabolickeGodine = metabolickeGodine;
	}

	public int getFizickoStanje() {
		return fizickoStanje;
	}

	public void setFizickoStanje(int fizickoStanje) {
		this.fizickoStanje = fizickoStanje;
	}

	public Date getDatumAnalize() {
		return datumAnalize;
	}

	public void setDatumAnalize(Date datumAnalize) {
		this.datumAnalize = datumAnalize;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	

}
