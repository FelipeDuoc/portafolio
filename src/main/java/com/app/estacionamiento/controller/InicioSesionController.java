package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.domain.Credenciales;

@Controller
public class InicioSesionController {
	
	@GetMapping(value="/iniciosesion")
	private ModelAndView inicioSesionPage(Model model) {
		ModelAndView myv = new ModelAndView();
		model.addAttribute("credenciales", new Credenciales());
		myv.setViewName("iniciosesion");
		return myv;
	}
	
	@GetMapping(value="/iniciosesion/inicio")
	private ModelAndView inicioSesionPost(HttpSession sesion, @ModelAttribute("credenciales") Credenciales credenciales) {
		
		
		ModelAndView myv = new ModelAndView("inicio");
		
		sesion.setAttribute("usuario", credenciales.getUser());
		sesion.setAttribute("pass", credenciales.getPass());
		sesion.setAttribute("rol", "admin");
		
		return myv;
	}
	
	@GetMapping(value="/cerrarsesion")
	private String cierreSesion(HttpSession sesion, @ModelAttribute("credenciales") Credenciales credenciales) {
		
		sesion.removeAttribute("usuario");
		sesion.removeAttribute("pass");
		sesion.removeAttribute("rol");
		sesion.invalidate();
		
		return "redirect:/iniciosesion";
	}
}
