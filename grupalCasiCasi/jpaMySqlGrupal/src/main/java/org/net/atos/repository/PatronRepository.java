package org.net.atos.repository;

import org.net.atos.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

	@Query(value = "SELECT * FROM patron WHERE dni = ?1", nativeQuery = true)
	Patron getPatronFromDNI(String dni);
	
}
