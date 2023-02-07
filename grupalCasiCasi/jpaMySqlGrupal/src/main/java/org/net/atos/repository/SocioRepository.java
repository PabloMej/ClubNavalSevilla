package org.net.atos.repository;

import org.net.atos.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

	@Query(value = "SELECT * FROM socio WHERE dni = ?1", nativeQuery = true)
	Socio getSocioFromDNI(String dni);
}
