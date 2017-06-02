package ba.fitandsit.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the narudzbe database table.
 * 
 */
@Entity
//@NamedQuery(name="Narudzbe.findAll", query="SELECT n FROM Narudzbe n")
public class Narudzbe {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long narudzbaID;

	private Integer aktivan;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datum;

	private Long korisnikID;

	private Long prodavacID;

	
	//@ManyToMany(mappedBy="narudzbeList")
    //@JsonManagedReference
	@Autowired
	@ManyToMany
	@JoinTable(
			name="`stavke_narudzbe`"
					, joinColumns={}

					
				,inverseJoinColumns=
					@JoinColumn(name="Proizvod_ID")
				)
	private List<Proizvodi> proizvodiList;

	public Narudzbe() {
	}

	public Long getNarudzbaID() {
		return this.narudzbaID;
	}

	public void setNarudzbaID(Long narudzbaID) {
		this.narudzbaID = narudzbaID;
	}

	public Integer getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(Integer aktivan) {
		this.aktivan = aktivan;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Long getKorisnikID() {
		return this.korisnikID;
	}

	public void setKorisnikID(Long korisnikID) {
		this.korisnikID = korisnikID;
	}

	public Long getProdavacID() {
		return this.prodavacID;
	}

	public void setProdavacID(Long prodavacID) {
		this.prodavacID = prodavacID;
	}

	public List<Proizvodi> getProizvodiList() {
		return this.proizvodiList;
	}

	public void setProizvodiList(List<Proizvodi> proizvodiList) {
		this.proizvodiList = proizvodiList;
	}

	public Narudzbe(Date datum, Long korisnik, Long prodavac, Integer aktivan)
	{
		this.datum=datum;
		this.korisnikID=korisnik;
		this.prodavacID=prodavac;
		this.aktivan=aktivan;
	}
	
	public Narudzbe(Date datum, Long korisnik, Long prodavac, Integer aktivan, List<Proizvodi>p)
	{
		this.datum=datum;
		this.korisnikID=korisnik;
		this.prodavacID=prodavac;
		this.aktivan=aktivan;
		this.proizvodiList=p;
	}
}