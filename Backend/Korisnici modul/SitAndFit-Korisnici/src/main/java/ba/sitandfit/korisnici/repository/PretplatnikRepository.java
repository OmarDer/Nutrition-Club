package ba.sitandfit.korisnici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.sitandfit.korisnici.model.Pretplatnik;

@Repository
public interface PretplatnikRepository extends JpaRepository<Pretplatnik, Long>{
	

}
