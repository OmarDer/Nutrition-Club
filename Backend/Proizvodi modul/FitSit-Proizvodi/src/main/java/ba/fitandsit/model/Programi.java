package ba.fitandsit.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programi database table.
 * 
 */
@Entity
@NamedQuery(name="Programi.findAll", query="SELECT p FROM Programi p")
public class Programi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int programID;

	private int aktivan;

	private int autorID;

	@Column(name="`Naziv programa`")
	private String naziv_programa;

	@Column(name="`Opis programa`")
	private String opis_programa;

	//bi-directional many-to-many association to Proizvodi
	@ManyToMany(mappedBy="programiList")
	private List<Proizvodi> proizvodiList;

	public Programi() {
	}

	public int getProgramID() {
		return this.programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public int getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public int getAutorID() {
		return this.autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}

	public String getNaziv_programa() {
		return this.naziv_programa;
	}

	public void setNaziv_programa(String naziv_programa) {
		this.naziv_programa = naziv_programa;
	}

	public String getOpis_programa() {
		return this.opis_programa;
	}

	public void setOpis_programa(String opis_programa) {
		this.opis_programa = opis_programa;
	}

	public List<Proizvodi> getProizvodiList() {
		return this.proizvodiList;
	}

	public void setProizvodiList(List<Proizvodi> proizvodiList) {
		this.proizvodiList = proizvodiList;
	}

}