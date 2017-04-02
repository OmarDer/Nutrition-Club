package ba.fitandsit.wrappers;

import ba.fitandsit.model.*;

public class JsonWrapperProizvodi {
	
		private String status;
	 
		private String poruka;
		
		private Proizvodi proizvod;

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

		public Proizvodi getProizvod() {
			return proizvod;
		}

		public void setProgram(Proizvodi proizvod) {
			this.proizvod = proizvod;
		}
		
		public JsonWrapperProizvodi(String status, String poruka, Proizvodi proizvod)
		{
			super();
			this.status=status;
			this.poruka=poruka;
			this.proizvod=proizvod;
		}
		
}
