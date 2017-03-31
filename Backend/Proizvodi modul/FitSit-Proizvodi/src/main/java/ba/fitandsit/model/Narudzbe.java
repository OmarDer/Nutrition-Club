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
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long narudzbaID;

	private int aktivan;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datum;

	private long korisnikID;

	private long prodavacID;

	
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

	public long getNarudzbaID() {
		return this.narudzbaID;
	}

	public void setNarudzbaID(long narudzbaID) {
		this.narudzbaID = narudzbaID;
	}

	public int getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public long getKorisnikID() {
		return this.korisnikID;
	}

	public void setKorisnikID(long korisnikID) {
		this.korisnikID = korisnikID;
	}

	public long getProdavacID() {
		return this.prodavacID;
	}

	public void setProdavacID(long prodavacID) {
		this.prodavacID = prodavacID;
	}

	public List<Proizvodi> getProizvodiList() {
		return this.proizvodiList;
	}

	public void setProizvodiList(List<Proizvodi> proizvodiList) {
		this.proizvodiList = proizvodiList;
	}

	public Narudzbe(Date datum, int korisnik, int prodavac, int aktivan)
	{
		this.datum=datum;
		this.korisnikID=korisnik;
		this.prodavacID=prodavac;
		this.aktivan=aktivan;
	}
	
	public Narudzbe(Date datum, int korisnik, int prodavac, int aktivan,List<Proizvodi>p)
	{
		this.datum=datum;
		this.korisnikID=korisnik;
		this.prodavacID=prodavac;
		this.aktivan=aktivan;
		this.proizvodiList=p;
	}
}