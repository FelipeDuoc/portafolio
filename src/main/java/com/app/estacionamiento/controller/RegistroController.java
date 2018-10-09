package com.app.estacionamiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.estacionamiento.domain.Registro;

@Controller
public class RegistroController {
	
	
	@GetMapping(value="/registro")
	private String Registro(Model model, 
							@RequestParam(name="ok",required=false) String ok,
							@RequestParam(name="nok",required=false) String nok) {
		
		model.addAttribute("ok",ok);
		model.addAttribute("nok", nok);
		model.addAttribute("registro", new Registro());
		return "registro";
	}
	
	@PostMapping(value="/registrar/registro")
	private String RegistroNuevo(Model model) {
		return "redirect:/registro?ok";
	}
}
