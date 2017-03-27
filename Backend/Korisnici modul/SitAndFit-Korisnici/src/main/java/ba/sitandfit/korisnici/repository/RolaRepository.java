package ba.sitandfit.korisnici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.sitandfit.korisnici.model.Rola;

@Repository
public interface RolaRepository extends JpaRepository<Rola, Long>{

}
