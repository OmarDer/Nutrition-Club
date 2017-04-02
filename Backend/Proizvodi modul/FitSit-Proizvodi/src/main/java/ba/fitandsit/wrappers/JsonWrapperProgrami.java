package ba.fitandsit.wrappers;

import ba.fitandsit.model.*;

public class JsonWrapperProgrami {
	private String status;
	 
	private String poruka;
	
	private Programi program;

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

	public Programi getProgram() {
		return program;
	}

	public void setProgram(Programi program) {
		this.program = program;
	}
	
	public JsonWrapperProgrami(String status, String poruka, Programi program)
	{
		super();
		this.status=status;
		this.poruka=poruka;
		this.program=program;
	}
	

	
}
