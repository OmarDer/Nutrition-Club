package ba.sitandfit.korisnici.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="role")
public class Rola {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ROLA_ID")
	private long id;
	private String nazivRole;
	private String opisRole;
	

	@OneToMany(mappedBy="rola")
	@JsonBackReference
	private List<Korisnik> korisnici;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivRole() {
		return nazivRole;
	}

	public void setNazivRole(String nazivRole) {
		this.nazivRole = nazivRole;
	}

	public String getOpisRole() {
		return opisRole;
	}

	public void setOpisRole(String opisRole) {
		this.opisRole = opisRole;
	}

	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	
}
