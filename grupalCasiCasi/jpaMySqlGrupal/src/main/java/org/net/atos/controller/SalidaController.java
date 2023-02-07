package org.net.atos.controller;

import java.util.List;

import org.net.atos.model.Barco;
import org.net.atos.model.Salida;
import org.net.atos.services.BarcoService;
import org.net.atos.services.PatronService;
import org.net.atos.services.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@RequestMapping("/salidas")
@Controller
public class SalidaController {

	@Autowired
	private SalidaService service;
	
	@Autowired
	private PatronService servicePatron;

	@Autowired
	private BarcoService serviceBarco;

	@GetMapping("/listSalidas")
	public String getListSalidas(Model model) {
		Barco barco = new Barco();
		model.addAttribute("barco", barco);
		model.addAttribute("salidas", service.getListSalidas());
		return "salidas/listSalidas";
	}

	@GetMapping("/listSalidasBarcos")
	public String getListSalidasBarco(Model model) {
		List<Barco> barcos = serviceBarco.getListBarcos();
		model.addAttribute("barcos", barcos);
		return "salidas/listSalidasBarcos";
	}

	@GetMapping("/listSalidasBarcos/{id}")
	public String getListSalidasBarcoId(@PathVariable("id") Long id, Model model) {
		List<Salida> salidas = service.getListSalidasByBoat(id);
		model.addAttribute("salidas", salidas);
		model.addAttribute("barco", serviceBarco.findBarcoById(id));
		return "salidas/listSalidasBarcosId";
	}

	@GetMapping("/createNewSalida")
	public String createNewSalida(Model model) {
		model.addAttribute("salida", new Salida());
		model.addAttribute("patrones", servicePatron.getAllPatrones());
		model.addAttribute("barcos", serviceBarco.getListBarcos());

		return "salidas/createNewSalida";
	}

	@PostMapping("/listSalidas")
	public String getListSalidasSubmit(Model model, @ModelAttribute @Valid Salida salida, BindingResult bindingResult) {
		Barco barco = new Barco();
		model.addAttribute("barco", barco);
		if (!bindingResult.hasErrors()) {
			service.createNewSalida(salida);
			model.addAttribute("salidas", service.getListSalidas());
			return "salidas/listSalidas";
		} else {
			return "salidas/createNewSalida";
		}
	}

	@PostMapping("/listarSalidasNombre")
	public String getSalidasBarcoFiltro(@ModelAttribute @Valid Barco barco, Model model) {
		List<Salida> salidas = service.getListSalidasByBoatName(barco.getNombre());
		model.addAttribute("salidas", salidas);
		return "salidas/listSalidas";
	}
}
