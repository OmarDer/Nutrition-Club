package com.fitandsit.Model;

import javax.persistence.*;

@Entity

public class tipvijesti {
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long tipID;

private String nazivTipa;

private Integer aktivan;

public tipvijesti(){
}
public Long getTipID(){
	return this.tipID;
}
public void setTipID(Long tipID){
	this.tipID = tipID;
}

public Integer getAktivan(){
	return this.aktivan;
}
public void setAktivan(Integer aktivan){
	this.aktivan = aktivan;
}

public String getNazivTipa(){
	return this.nazivTipa;
}
public void setNazivTipa(String naziv){
	this.nazivTipa = naziv;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

}
