package com.app.estacionamiento.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.domain.Credenciales;

@Controller
public class IndexController {
	
	@GetMapping(value="/test")
	private ModelAndView IndexCon(HttpSession sesion) {
		ModelAndView myv = new ModelAndView();
		
		if(sesion.getId().isEmpty()) {
			myv.setViewName("iniciosesion");
		}else {
			myv.setViewName("inicio");
		}
		
		return myv;
	}
	
	@GetMapping(value="/search")
	private ModelAndView search(HttpSession sesion) {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("searchLatLon");;
		return myv;
	}
	
	@GetMapping(value="/index")
	private ModelAndView indexPage(HttpSession sesion) {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("index");;
		return myv;
	}
	
	@GetMapping(value="/inicio")
	private String inicioSesionPage(HttpSession sesion, Model model) {
		if(sesion.getAttribute("rol")!=null) {
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "inicio";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@GetMapping(value="/indexconsultor")
	private String ConsultorPage(HttpSession sesion, Model model) {
			return "indexconsultor";
	}
	
	
}
