package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.domain.Arriendo;

@Controller
public class IndexController {
	
	@Autowired
	private ArriendoDao arriendodao;
	
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
	private String inicioSesionPage(HttpSession sesion, 
									Model model,
									@RequestParam(value="anok", required=false) String anok) {
		if(sesion.getAttribute("rol")!=null) {
			Integer idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			
			Arriendo arriendo = arriendodao.arriendoActivo(idPersona);
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());

			if(arriendo!=null) {
				model.addAttribute("idArriendo", arriendo.getIdArriendo());
				return "inicioArriendoActivo";			
			}else {
				model.addAttribute("anok", anok);
				return "inicio";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@GetMapping(value="/indexconsultor")
	private String ConsultorPage(HttpSession sesion, Model model) {
			return "indexconsultor";
	}
	
	
}
