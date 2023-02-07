package org.net.atos.services;

import java.util.List;
import java.util.Optional;

import org.net.atos.model.Patron;
import org.net.atos.model.Salida;
import org.net.atos.model.Socio;
import org.net.atos.repository.PatronRepository;
import org.net.atos.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatronService {

	@Autowired
	private PatronRepository repository;

	@Autowired
	private SalidaRepository salidaRepository;

	public List<Patron> getAllPatrones() {
		return repository.findAll();
	}

	public Patron createNewPatron(Patron patron) {
		return repository.save(patron);
	}

	public void createNewPatronFromSocio(Socio socio) {
		// Comprueba si el patrón ya existe
		if (repository.getPatronFromDNI(socio.getDni()) == null) {
			// Crea el patrón solo si no existe
			Patron patronCreado = new Patron(socio.getNombre(), socio.getDni(), socio.getTelefono(), socio.getEmail());
			repository.save(patronCreado);
		}
	}

	public Optional<Patron> getById(Long id) {
		return repository.findById(id);
	}

	/**
	 * Se borra al patrón.
	 * Si ese patrón posee salidas asociadas, estas se borrarán también porque se entiende que 
	 * no se quiere tener registros de salidas que no tengan asociadas un patrón.
	 * @param patron
	 */
	public void deletePatron(Patron patron) {
		List<Salida>salidasPatron = salidaRepository.getSalidasFromPatron(patron.getId());
		if(salidasPatron != null) {
			for (Salida salida : salidasPatron) {
				//salida.setBarco(null);
				salida.setPatron(null);
				salidaRepository.save(salida);
			}
		}
		repository.delete(patron);
	}

}
