package org.net.atos.services;

import java.util.List;
import java.util.Optional;

import org.net.atos.model.Barco;
import org.net.atos.model.Salida;
import org.net.atos.repository.BarcoRepository;
import org.net.atos.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarcoService {

	@Autowired
	private BarcoRepository repository;
	@Autowired
	private SalidaRepository salidaRepository;

	public List<Barco> getListBarcos() {
		return repository.findAll();
	}

	public Barco createNewBarco(Barco barco) {
		return repository.save(barco);
	}

	public Optional<Barco> getById(Long id) {
		return repository.findById(id);
	}

	public Barco modificaBarco(Barco s) {
		Barco barcoModificado = null;
		repository.save(s);
		return barcoModificado;
	}

	public void deleteBarco(Barco barco) {
		barco.setSocio(null);
		//Elimina salidas del barco
		if (barco.getSalidas() != null && !barco.getSalidas().isEmpty()) {
			for (Salida salida : barco.getSalidas()) {
				salida.setBarco(null);
				salidaRepository.save(salida);
			}
			repository.delete(barco);
			barco.setSalidas(null);
		}
		//Elimina barco de la lista de barcos del socio asociado
		/*if (barco.getSocio() != null) {
			for (Barco b : barco.getSocio().getBarcos()) {
				if (b.getId().equals(barco.getId())) {
					barco.getSocio().getBarcos().remove(barco.getSocio().getBarcos().indexOf(barco));// lo pongo así
																										// para que no
																										// de problemas
																										// el remove por
																										// objeto. Mejor
																										// por índice
				}
			}
		}*/
		repository.delete(barco);
	}
	
	public Barco findBarcoById(Long id) {
		return repository.findBarcoById(id);
	}

}
