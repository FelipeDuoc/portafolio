package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.Arriendo;
import com.app.estacionamiento.domain.EstacionamientoObjBD;

@Controller
public class IndexController {
	
	@Autowired
	private ArriendoDao arriendodao;
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
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
									@RequestParam(value="anok", required=false) String anok,
									@RequestParam(value="cnok", required=false) String cnok,
									@RequestParam(value="cok", required=false) String cok) {
		if(sesion.getAttribute("rol")!=null) {
			
			Integer idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			
			Integer idRol = Integer.parseInt((String) sesion.getAttribute("rol"));
			
			if(idRol.equals(3)) {
				List <EstacionamientoObjBD> lista = estacionamientoDao.getParkingInUse(idPersona);
				model.addAttribute("lista", lista);
				return "estadoestacionamientos";
			}else {
				Arriendo arriendo = arriendodao.arriendoActivo(idPersona);
				model.addAttribute("nombre",sesion.getAttribute("nombre").toString());

				if(arriendo!=null) {
					model.addAttribute("idArriendo", arriendo.getIdArriendo());
					return "inicioArriendoActivo";			
				}else {
					model.addAttribute("anok", anok);
					model.addAttribute("cok", cok);
					model.addAttribute("cnok", cnok);
					return "inicio";
				}
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
