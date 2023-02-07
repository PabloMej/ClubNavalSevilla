package org.net.atos.controller;

import org.net.atos.model.Socio;
import org.net.atos.services.PatronService;
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
@RequestMapping("/socios")
public class SocioController {

	@Autowired
	private SocioService service;

	@Autowired
	private PatronService patronService;

	@GetMapping("/listSocios")
	public String getListSocios(Model model) {
		model.addAttribute("socios", service.getAllSocios());
		return "socios/listSocios";
	}

	/**
	 * Crea a un socio. Si se ha marcado la casilla de patrón, creará al patrón
	 * también solo si no está creado
	 * 
	 * @param model
	 * @param socio
	 * @param bindingResult
	 * @return lista de socios con el nuevo socio (y patrón) creado
	 */
	@PostMapping("/listSocios")
	public String getListSociosSubmit(Model model, @ModelAttribute @Valid Socio socio, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			service.createSocio(socio);
			if (socio.getPatron()) {
				patronService.createNewPatronFromSocio(socio);
			}
			model.addAttribute("socios", service.getAllSocios());
			return "socios/listSocios";
		} else {
			return "socios/createNewSocio";
		}
	}

	@GetMapping("/createNewSocio")
	public String createNewSocio(Model model) {
		model.addAttribute("socio", new Socio());
		return "socios/createNewSocio";
	}

	@GetMapping("/editSocio/{id}")
	public String createNewSocioSubmit(@PathVariable("id") Long id, Model model) {
		Socio socio = service.getById(id).orElse(null);
		model.addAttribute("socio", socio);
		model.addAttribute("id", id);
		return "socios/editSocio";
	}

	@PostMapping("/editSocio") // el server no me admite métodos put
	public String getListSociosAfterEdit(@ModelAttribute @Valid Socio socio, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			service.createSocio(socio);
			model.addAttribute("socios", service.getAllSocios());
			return "socios/listSocios";
		}else {
			return "socios/editSocio";
		}
	}

	@GetMapping("/deleteSocio/{id}")
	public String deleteSocio(Model model, @PathVariable("id") Long id) {
		Socio socio = service.getById(id).orElse(null);
		service.deleteSocio(socio);
		model.addAttribute("socios", service.getAllSocios());
		return "socios/listSocios";
	}

}
