package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping(value="/registro")
	private ModelAndView Registro() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("registro");;
		return myv;
	}
	
	@GetMapping(value="/nuevoestacionamiento")
	private ModelAndView nuevoEstacionamientoView() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("nuevoestacionamiento");;
		return myv;
	}
}
