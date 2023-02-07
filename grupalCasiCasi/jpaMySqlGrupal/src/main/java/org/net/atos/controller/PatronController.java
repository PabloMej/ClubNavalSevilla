package org.net.atos.controller;

import org.net.atos.model.Patron;
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
@RequestMapping("/patrones")
public class PatronController {

	@Autowired
	private PatronService service;
	@Autowired
	private SocioService socioService;

	@GetMapping("/listPatrones")
	public String getListPatrones(Model model) {
		model.addAttribute("patrones", service.getAllPatrones());
		return "patrones/listPatrones";
	}

	@GetMapping("/createNewPatron")
	public String createNewPatron(Model model) {
		model.addAttribute("patron", new Patron());
		return "patrones/createNewPatron";
	}

	/**
	 * Crea un patrón. Solo creará al socio si se ha marcado la casilla de socio y
	 * este no existe ya en bbdd
	 * 
	 * @param model
	 * @param patron
	 * @param bindingResult
	 * @return lista de patrones con socio creado si se ha marcado
	 */
	@PostMapping("/listPatrones")
	public String getListPatronesSubmit(Model model, @ModelAttribute @Valid Patron patron, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			service.createNewPatron(patron);
			if (patron.getSocio()) {
				socioService.createNewSocioFromPatron(patron);
			}
			model.addAttribute("patrones", service.getAllPatrones());
			return "patrones/listPatrones";
		} else {
			return "patrones/createNewPatron";
		}
	}
	
	@GetMapping("/editPatron/{id}")
	public String createNewPatronSubmit(@PathVariable("id") Long id, Model model) {
		Patron patron = service.getById(id).orElse(null);
		model.addAttribute("patron", patron);
		model.addAttribute("id", id);
		return "patrones/editPatron";
	}

	@PostMapping("/editPatron") // el server no me admite métodos put
	public String getListPatronesAfterEdit(@ModelAttribute @Valid Patron patron, BindingResult result, Model model) {
		service.createNewPatron(patron);
		model.addAttribute("patrones", service.getAllPatrones());
		return "patrones/listPatrones";
	}
	
	@GetMapping("/deletePatron/{id}")
	public String deletePatron(Model model, @PathVariable("id") Long id) {
		Patron patron = service.getById(id).orElse(null);
		service.deletePatron(patron);
		model.addAttribute("patrones", service.getAllPatrones());
		return "patrones/listPatrones";
	}

}
