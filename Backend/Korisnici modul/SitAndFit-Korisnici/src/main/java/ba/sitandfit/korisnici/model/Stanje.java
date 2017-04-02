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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "stanja_korisnika")
public class Stanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="STANJE_ID")
	private Long id;
	private Double tezina;
	private Double procenatMasnoce;
	private Double organskeMasnoce;
	private Double postotakMisica;
	private Double procenatVode;
	private Double bazalniMetabolizam;
	private Double kostanaMasa;
	private Double metabolickeGodine;
	private Integer fizickoStanje;
	private Boolean aktivno;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datumAnalize;
	
	@ManyToOne
	@JoinColumn(name="KORISNIK_ID")
	@JsonBackReference
	private Korisnik korisnik;
	
	
	public Boolean getAktivno() {
		return aktivno;
	}

	public void setAktivno(Boolean aktivno) {
		this.aktivno = aktivno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTezina() {
		return tezina;
	}
	
	public Double getProcenatMasnoce() {
		return procenatMasnoce;
	}

	public void setProcenatMasnoce(Double procenatMasnoce) {
		this.procenatMasnoce = procenatMasnoce;
	}

	public Double getOrganskeMasnoce() {
		return organskeMasnoce;
	}

	public void setOrganskeMasnoce(Double organskeMasnoce) {
		this.organskeMasnoce = organskeMasnoce;
	}

	public Double getPostotakMisica() {
		return postotakMisica;
	}

	public void setPostotakMisica(Double postotakMisica) {
		this.postotakMisica = postotakMisica;
	}

	public Double getProcenatVode() {
		return procenatVode;
	}

	public void setProcenatVode(Double procenatVode) {
		this.procenatVode = procenatVode;
	}

	public Double getBazalniMetabolizam() {
		return bazalniMetabolizam;
	}

	public void setBazalniMetabolizam(Double bazalniMetabolizam) {
		this.bazalniMetabolizam = bazalniMetabolizam;
	}

	public Double getKostanaMasa() {
		return kostanaMasa;
	}

	public void setKostanaMasa(Double kostanaMasa) {
		this.kostanaMasa = kostanaMasa;
	}

	public Double getMetabolickeGodine() {
		return metabolickeGodine;
	}

	public void setMetabolickeGodine(Double metabolickeGodine) {
		this.metabolickeGodine = metabolickeGodine;
	}

	public Integer getFizickoStanje() {
		return fizickoStanje;
	}

	public void setFizickoStanje(Integer fizickoStanje) {
		this.fizickoStanje = fizickoStanje;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
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
