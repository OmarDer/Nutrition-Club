package ba.fitandsit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proizvodi database table.
 * 
 */
@Entity
@NamedQuery(name="Proizvodi.findAll", query="SELECT p FROM Proizvodi p")
public class Proizvodi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`Proizvod ID`")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int proizvod_ID;

	private int aktivan;

	@Column(name="`Autor ID`")
	private int autor_ID;

	private double cijena;

	@Column(name="`Naziv proizvoda`")
	private String naziv_proizvoda;

	@Column(name="`Opis proizvoda`")
	private String opis_proizvoda;

	private String slika;

	//bi-directional many-to-many association to Narudzbe
	@ManyToMany
	@JoinTable(
		name="`stavke narudzbe`"
		, joinColumns={

			}
		, inverseJoinColumns={
			@JoinColumn(name="NarudzbaID")
			}
		)
	private List<Narudzbe> narudzbeList;

	//bi-directional many-to-many association to Programi
	@ManyToMany
	@JoinTable(
		name="`stavke programa`"
		, joinColumns={

			}
		, inverseJoinColumns={
			@JoinColumn(name="ProgramID")
			}
		)
	private List<Programi> programiList;

	public Proizvodi() {
	}

	public int getProizvod_ID() {
		return this.proizvod_ID;
	}

	public void setProizvod_ID(int proizvod_ID) {
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

}