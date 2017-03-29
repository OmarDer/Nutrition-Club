package ba.fitandsit.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the programi database table.
 * 
 */
@Entity
//@NamedQuery(name="Programi.findAll", query="SELECT p FROM Programi p")
public class Programi {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long programID;

	private int aktivan;

	private int autorID;

	@Column(name="`Naziv programa`")
	private String naziv_programa;

	@Column(name="`Opis programa`")
	private String opis_programa;

	@Autowired
	@ManyToMany
	@JoinTable(
	name="`stavke_programa`"
			, joinColumns={}

			
		,inverseJoinColumns=
			@JoinColumn(name="Proizvod_ID")
		)
	private List<Proizvodi> proizvodiList;

	public Programi() {
	}

	public long getProgramID() {
		return this.programID;
	}

	public void setProgramID(long programID) {
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
	
	public Programi(long id,String ime, String opis, int autor, int aktivan){
		this.naziv_programa=ime;
		this.opis_programa=opis;
		this.autorID=autor;
		this.aktivan=aktivan;
		this.programID=id;
		
	}
	
	public Programi(long id,String ime, String opis, int autor, int aktivan,List<Proizvodi>p){
		this.naziv_programa=ime;
		this.opis_programa=opis;
		this.autorID=autor;
		this.aktivan=aktivan;
		this.proizvodiList=p;
		this.programID=id;
		
	}

}