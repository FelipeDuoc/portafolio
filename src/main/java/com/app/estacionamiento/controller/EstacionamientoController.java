package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstacionamientoController {
	
	@GetMapping(value="/nuevoestacionamiento")
	private ModelAndView nuevoEstacionamientoView() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("nuevoestacionamiento");;
		return myv;
	}
	
	@GetMapping(value="/misestacionamientos")
	private String vehiculosList(HttpSession sesion, Model model) {
		if(sesion.getAttribute("rol")!=null) {
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "listaestacionamientos";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	
}
