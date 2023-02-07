package org.net.atos.repository;

import java.util.List;

import org.net.atos.model.Barco;
import org.net.atos.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcoRepository extends JpaRepository<Barco, Long> {
	public Barco findBarcoById(Long id);

}
