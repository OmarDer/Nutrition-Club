package ba.fitandsit.wrappers;

import java.util.List;

import ba.fitandsit.model.*;

public class JsonWrapperListProgrami {
	
	private String status;
	private String poruka;
	private List<Programi> programi;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public List<Programi> getProgrami() {
		return programi;
	}
	public void setProgrami(List<Programi> programi) {
		this.programi = programi;
	}
	public JsonWrapperListProgrami(String status,String poruka)
	{
		this.status=status;
		this.poruka=poruka;
		
	}
	public JsonWrapperListProgrami(String status,String poruka, List<Programi> programi)
	{
		this.status=status;
		this.poruka=poruka;
		this.programi=programi;
	}
}
