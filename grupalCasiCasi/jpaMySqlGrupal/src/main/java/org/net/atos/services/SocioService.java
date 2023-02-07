package org.net.atos.services;

import java.util.List;
import java.util.Optional;

import org.net.atos.model.Barco;
import org.net.atos.model.Patron;
import org.net.atos.model.Socio;
import org.net.atos.repository.BarcoRepository;
import org.net.atos.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioService {

	@Autowired
	private SocioRepository repository;

	@Autowired
	private BarcoRepository barcoRepository;

	public List<Socio> getAllSocios() {
		return repository.findAll();
	}

	public Socio createSocio(Socio s) {
		return repository.save(s);
	}

	public Socio modificaSocio(Socio s) {
		Socio socioModificado = null;
		for (Socio socio : repository.findAll()) {
			if (socio.getId() == s.getId()) {
				socio.setId(s.getId());
				socioModificado = repository.save(socio);
			}
		}
		return socioModificado;
	}

	public Optional<Socio> getById(Long id) {
		return repository.findById(id);
	}

	public void deleteSocio(Socio socio) {// Entiendo que el barco se borra tambiél al borrar a su socio propietario
		if (socio.getBarcos() != null && !socio.getBarcos().isEmpty()) {
			for (Barco barco : socio.getBarcos()) {
				barco.setSocio(null);
				barcoRepository.delete(barco);
			}
		}
		repository.delete(socio);
	}

	public void createNewSocioFromPatron(Patron patron) {
		// Comprueba si el socio ya existe
		if (repository.getSocioFromDNI(patron.getDni()) == null) {
			// Crea el socio solo si no existe
			Socio socioCreado = new Socio(patron.getNombre(), patron.getDni(), patron.getTelefono(), patron.getEmail());
			repository.save(socioCreado);
		}
	}
}
