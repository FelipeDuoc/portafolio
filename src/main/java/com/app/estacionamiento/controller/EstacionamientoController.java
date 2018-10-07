package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.Estacionamiento;

@Controller
public class EstacionamientoController {
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
	@GetMapping(value="/nuevoestacionamiento")
	private ModelAndView nuevoEstacionamientoView() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("nuevoestacionamiento");;
		return myv;
	}
	
	@GetMapping(value="/misestacionamientos")
	private String vehiculosList(HttpSession sesion, Model model) {
		if(sesion.getAttribute("rol")!=null) {
			int idPersona;
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			List<Estacionamiento> lista = estacionamientoDao.getAllParking(idPersona);
			
			model.addAttribute("lista",lista);
			
			return "listaestacionamientos";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	
}
