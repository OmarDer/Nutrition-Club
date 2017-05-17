package ba.sitandfit.korisnici.communication;

public interface ProizvodiREST {
	
	String getProgramiByKorisnikId(Long korisnikId);
	String napraviTokenZaKomunikaciju();
	String connectToProizvodi(String url,Long id);
}
