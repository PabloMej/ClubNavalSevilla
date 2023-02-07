package org.net.atos.repository;

import java.util.List;

import org.net.atos.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {
	
	@Query(value = "SELECT * FROM salida WHERE patron_id = ?1", nativeQuery = true)
	List<Salida> getSalidasFromPatron(Long long1);
	
	@Query(value = "SELECT * FROM salida NATURAL JOIN barco WHERE barco.id = ?1", nativeQuery = true)
	public List<Salida> getSalidaByBoat(Long idBarco);
	
	@Query(value = "SELECT * FROM salida NATURAL JOIN barco WHERE barco.nombre = ?1", nativeQuery = true)
	public List<Salida> getListSalidasByBoatName(String nombreBarco);

}
