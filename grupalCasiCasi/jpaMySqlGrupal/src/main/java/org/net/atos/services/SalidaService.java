package org.net.atos.services;

import java.util.List;

import org.net.atos.model.Salida;
import org.net.atos.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalidaService {
	
	@Autowired
	private SalidaRepository repository;
	
	public List<Salida> getListSalidas(){
		return repository.findAll();
	}
	
	public Salida createNewSalida(Salida salida) {
		return repository.save(salida);
	}
	
	public List<Salida> getListSalidasByBoat( Long idBarco){
		return repository.getSalidaByBoat(idBarco);
	}
	
	public List<Salida> getListSalidasByBoatName(String nombreBarco){
		return repository.getListSalidasByBoatName(nombreBarco);
	}

}
