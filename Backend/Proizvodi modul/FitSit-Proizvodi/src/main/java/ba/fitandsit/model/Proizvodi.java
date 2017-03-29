package ba.fitandsit.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the proizvodi database table.
 * 
 */
@Entity
//@NamedQuery(name="Proizvodi.findAll", query="SELECT p FROM Proizvodi p")
public class Proizvodi {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`Proizvod_ID`")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long proizvod_ID;

	private int aktivan;

	@Column(name="`Autor ID`")
	private int autor_ID;

	private double cijena;

	@Column(name="`Naziv proizvoda`")
	private String naziv_proizvoda;

	@Column(name="`Opis proizvoda`")
	private String opis_proizvoda;

	private String slika;

	@ManyToMany(mappedBy="proizvodiList")
	@Autowired
	@JsonIgnore
	private List<Narudzbe> narudzbeList;

	
	@ManyToMany(mappedBy="proizvodiList")
	@Autowired
	@JsonIgnore
	private List<Programi> programiList;

	public Proizvodi() {
	}

	public long getProizvod_ID() {
		return this.proizvod_ID;
	}

	public void setProizvod_ID(long proizvod_ID) {
		this.proizvod_ID = proizvod_ID;
	}

	public int getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public int getAutor_ID() {
		return this.autor_ID;
	}

	public void setAutor_ID(int autor_ID) {
		this.autor_ID = autor_ID;
	}

	public double getCijena() {
		return this.cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public String getNaziv_proizvoda() {
		return this.naziv_proizvoda;
	}

	public void setNaziv_proizvoda(String naziv_proizvoda) {
		this.naziv_proizvoda = naziv_proizvoda;
	}

	public String getOpis_proizvoda() {
		return this.opis_proizvoda;
	}

	public void setOpis_proizvoda(String opis_proizvoda) {
		this.opis_proizvoda = opis_proizvoda;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public List<Narudzbe> getNarudzbeList() {
		return this.narudzbeList;
	}

	public void setNarudzbeList(List<Narudzbe> narudzbeList) {
		this.narudzbeList = narudzbeList;
	}

	public List<Programi> getProgramiList() {
		return this.programiList;
	}

	public void setProgramiList(List<Programi> programiList) {
		this.programiList = programiList;
	}
	
	public Proizvodi(long id,String ime,String opis,Double cijena,String slika, int akti, int autor){
		this.naziv_proizvoda=ime;
		this.opis_proizvoda=opis;
		this.slika=slika;
		this.cijena=cijena;
		this.aktivan=akti;
		this.autor_ID=autor;
		this.proizvod_ID=id;
	}
	
	public Proizvodi(long id,String ime,String opis,Double cijena,String slika, int akti, int autor, List<Programi>pr){
		this.naziv_proizvoda=ime;
		this.opis_proizvoda=opis;
		this.slika=slika;
		this.cijena=cijena;
		this.aktivan=akti;
		this.autor_ID=autor;
		this.programiList=pr;
		this.proizvod_ID=id;
	}
	
	public Proizvodi(long id,String ime,String opis,Double cijena,String slika, int akti, List<Narudzbe>nar, int autor){
		this.naziv_proizvoda=ime;
		this.opis_proizvoda=opis;
		this.slika=slika;
		this.cijena=cijena;
		this.aktivan=akti;
		this.autor_ID=autor;
		this.narudzbeList=nar;
		this.proizvod_ID=id;
	}
	public Proizvodi(long id,String ime,String opis,Double cijena,String slika, int akti,int autor, List<Programi>pr, List<Narudzbe>nar){
		this.naziv_proizvoda=ime;
		this.opis_proizvoda=opis;
		this.slika=slika;
		this.cijena=cijena;
		this.aktivan=akti;
		this.autor_ID=autor;
		this.narudzbeList=nar;
		this.programiList=pr;
		this.proizvod_ID=id;
	}

}