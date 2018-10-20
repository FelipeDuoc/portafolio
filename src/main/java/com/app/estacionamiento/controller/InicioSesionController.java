package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.dao.SesionDao;
import com.app.estacionamiento.domain.Arriendo;
import com.app.estacionamiento.domain.Calificacion;
import com.app.estacionamiento.domain.Credenciales;
import com.app.estacionamiento.domain.CredencialesResp;
import com.app.estacionamiento.domain.EstacionamientoObjBD;

@Controller
public class InicioSesionController {
	
	@Autowired
	private SesionDao sesiondao;
	
	@Autowired
	private ArriendoDao arriendodao;
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
	@GetMapping(value="/iniciosesion")
	private ModelAndView inicioSesionPage(Model model, 
										@RequestParam(name="error",required=false) String error,
										@RequestParam(name="nop",required=false) String nop) {
		ModelAndView myv = new ModelAndView();
		model.addAttribute("credenciales", new Credenciales());
		model.addAttribute("error",error);
		model.addAttribute("nop",nop);
		myv.setViewName("iniciosesion");
		return myv;
	}
	
	@PostMapping(value="/iniciosesion/inicio")
	private String inicioSesionPost(Model model, 
									HttpSession sesion, 
									@ModelAttribute("credenciales") Credenciales credenciales,
									@RequestParam(value="anok",required=false) String anok) {
		
		CredencialesResp cr = sesiondao.inicioSesion(credenciales);
		
		if(cr!=null) {
			sesion.setAttribute("usuario", cr.getIdUsuario());
			sesion.setAttribute("rol", cr.getIdRol());
			sesion.setAttribute("nombre", cr.getNombrePersona());
			sesion.setAttribute("persona", cr.getIdPersona());
			
			Integer idPersona = Integer.parseInt(cr.getIdPersona());
			
			Calificacion cal = sesiondao.getCalification(idPersona);
			
			Double calcliente = Double.parseDouble(cal.getPromedio_cliente().replace(",", "."));
			Double caldueno = Double.parseDouble(cal.getPromedio_dueno().replace(",", "."));
			
			Long starcliente = Math.round(calcliente);
			sesion.setAttribute("starcliente", starcliente);
			
			Long stardueno = Math.round(caldueno);
			sesion.setAttribute("stardueno", stardueno);
			
			sesion.setAttribute("calificacionCliente", calcliente);
			sesion.setAttribute("calificacionDueno", caldueno);
			sesion.setAttribute("starcliente", starcliente);
			sesion.setAttribute("stardueno", stardueno);
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			
			Integer idRol = Integer.parseInt(cr.getIdRol());
			
			if(idRol.equals(3)) {
				List <EstacionamientoObjBD> lista = estacionamientoDao.getParkingInUse(idPersona);
				model.addAttribute("lista", lista);
				return "estadoestacionamientos";
			}else {
				Arriendo arriendo = arriendodao.arriendoActivo(idPersona);
				if(arriendo!=null) {
					model.addAttribute("idArriendo", arriendo.getIdArriendo());
					return "inicioArriendoActivo";			
				}else {
					model.addAttribute("anok", anok);
					return "inicio";
				}
			}
			
			
		}else {
			return "redirect:/iniciosesion?error";
		}
	}
	
	@GetMapping(value="/cerrarsesion")
	private String cierreSesion(HttpSession sesion, @ModelAttribute("credenciales") Credenciales credenciales) {
		
		sesion.removeAttribute("usuario");
		sesion.removeAttribute("pass");
		sesion.removeAttribute("rol");
		sesion.removeAttribute("persona");
		sesion.removeAttribute("calificacionCliente");
		sesion.removeAttribute("calificacionDueno");
		sesion.invalidate();
		
		return "redirect:/iniciosesion";
	}
}
