package org.net.atos.controller;

import org.net.atos.model.Barco;
import org.net.atos.services.BarcoService;
import org.net.atos.services.SocioService;
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

@Controller
@RequestMapping("/barcos")
public class BarcoController {
	
	@Autowired
	private BarcoService service;
	
	@Autowired
	private SocioService socioService;
	
	@GetMapping("/listBarcos")
	public String getListBarcos(Model model) {
		model.addAttribute("barcos", service.getListBarcos());
		return "/barcos/listBarcos";
	}
	
	@GetMapping("/createNewBarco")
	public String createNewbarco(Model model) {
		model.addAttribute("barco", new Barco());
		model.addAttribute("sociosTotales", socioService.getAllSocios());
		return "barcos/createNewBarco";
	}
	
	@PostMapping("/createNewBarco")
	public String createNewbarcoSubmit(@ModelAttribute @Valid Barco barco, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			service.createNewBarco(barco);
			model.addAttribute("barcos", service.getListBarcos());
			return "barcos/listBarcos";
		}else {
			return "barcos/createNewBarco";
		}
	}
	
	@GetMapping("/editBarco/{id}")
	public String editNewBarcoSubmit(@PathVariable("id") Long id, Model model) {
		Barco barco = service.getById(id).orElse(null);
		model.addAttribute("barco", barco);
		model.addAttribute("socios", socioService.getAllSocios());
		model.addAttribute("id", id);
		return "barcos/editBarco";
	}
	
	@PostMapping("/editBarco")//el server no me admite métodos put
	public String getListBarcoAfterEdit(@ModelAttribute @Valid Barco barco, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			service.modificaBarco(barco);
			model.addAttribute("barcos", service.getListBarcos());
			return "barcos/listBarcos";
		}else {
			return "barcos/editBarco";
		}
	}
	
	@GetMapping("/deleteBarco/{id}")
	public String deleteSocio(Model model, @PathVariable("id") Long id) {
		Barco barco = service.getById(id).orElse(null);
		service.deleteBarco(barco) ;
		model.addAttribute("barcos", service.getListBarcos());
		return "barcos/listBarcos";
	}

}
