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
	private int narudzbaID;

	private int aktivan;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datum;

	private int korisnikID;

	private int prodavacID;

	//bi-directional many-to-many association to Proizvodi
	@Autowired
	@ManyToMany(mappedBy="narudzbeList")
	@JsonManagedReference
	private List<Proizvodi> proizvodiList;

	public Narudzbe() {
	}

	public int getNarudzbaID() {
		return this.narudzbaID;
	}

	public void setNarudzbaID(int narudzbaID) {
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

	public int getKorisnikID() {
		return this.korisnikID;
	}

	public void setKorisnikID(int korisnikID) {
		this.korisnikID = korisnikID;
	}

	public int getProdavacID() {
		return this.prodavacID;
	}

	public void setProdavacID(int prodavacID) {
		this.prodavacID = prodavacID;
	}

	public List<Proizvodi> getProizvodiList() {
		return this.proizvodiList;
	}

	public void setProizvodiList(List<Proizvodi> proizvodiList) {
		this.proizvodiList = proizvodiList;
	}

}