package ba.sitandfit.korisnici.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pretplatnici")
public class Pretplatnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PRETPLATNIK_ID")
	private Long id;
	
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date datumPretplate;
	private Boolean aktivan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatumPretplate() {
		return datumPretplate;
	}
	public void setDatumPretplate(Date datumPretplate) {
		this.datumPretplate = datumPretplate;
	}
	public Boolean getAktivan() {
		return aktivan;
	}
	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	

}
