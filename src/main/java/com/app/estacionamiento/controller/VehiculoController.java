package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.estacionamiento.dao.VehiculoDao;
import com.app.estacionamiento.domain.Vehiculo;

@Controller
public class VehiculoController {
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@GetMapping(value="/misvehiculos")
	private String vehiculosview(HttpSession sesion, Model model,
								@RequestParam(name="dok",required=false) String dok,
								@RequestParam(name="dnok",required=false) String dnok,
								@RequestParam(name="uok",required=false) String uok,
								@RequestParam(name="unok",required=false) String unok) {
		int id_usuario;
		if(sesion.getAttribute("rol")!=null) {
			id_usuario =Integer.parseInt((String) sesion.getAttribute("persona"));
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			
			List<Vehiculo> lista = vehiculoDao.getAllVehicles(id_usuario);
			model.addAttribute("lista",lista);
			model.addAttribute("dok",dok);
			model.addAttribute("dnok",dnok);
			model.addAttribute("uok",uok);
			model.addAttribute("unok",unok);
			
			return "listavehiculos";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@GetMapping(value="/nuevovehiculo")
	private String vehiculosNewPage(HttpSession sesion, 
									Model model, 
									@RequestParam(name="OK",required=false) String ok,
									@RequestParam(name="NOK",required=false) String nok) {
		if(sesion.getAttribute("rol")!=null) {
			model.addAttribute("ok",ok);
			model.addAttribute("nok",nok);
			model.addAttribute("vehiculo",new Vehiculo());
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "nuevovehiculo";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@RequestMapping(value="/actualizavehiculo", method = { RequestMethod.POST, RequestMethod.GET })
	private String vehiculosUpdatePage(HttpSession sesion, 
									Model model, 
									@RequestParam(name="OK",required=false) String ok,
									@RequestParam(name="NOK",required=false) String nok,
									@RequestParam(name="mod",required=false) String mod,
									@RequestParam(name="idVehiculo",required=true) int idVehiculo) {
		
		if(sesion.getAttribute("rol")!=null) {
			int idPersona;
			Vehiculo veh = new Vehiculo();
			
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			veh = vehiculoDao.getVehiculexId(idPersona, idVehiculo);
			
			model.addAttribute("ok",ok);
			model.addAttribute("nok",nok);
			model.addAttribute("vehiculo",veh);
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "actualizavehiculo";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}

	@PostMapping(value="/nuevovehiculo/crear")
	private String creaVehiculo(HttpSession sesion, Model model,@ModelAttribute("vehiculo") Vehiculo vehiculo ) {
		int idPersona;
		if(sesion.getAttribute("rol")!=null) {
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			int resultado = vehiculoDao.createVehicle(vehiculo, idPersona);
			if(resultado==1) {
				return "redirect:/nuevovehiculo?OK";
			}else {
				return "redirect:/nuevovehiculo?NOK";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@PostMapping(value="/actualizavehiculo/actualiza")
	private String vehiculosUpdate(HttpSession sesion, Model model,@ModelAttribute("vehiculo") Vehiculo vehiculo ) {
		if(sesion.getAttribute("rol")!=null) {
			int resultado = vehiculoDao.updateVehicle(vehiculo);
			if(resultado==1) {
				return "redirect:/misvehiculos?uok";
			}else {
				return "redirect:/misvehiculos?unok";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@PostMapping(value="/eliminavehiculo/elimina")
	private String deleteVehiculo(HttpSession sesion, Model model, int idVehiculo){
		if(sesion.getAttribute("rol")!=null) {
			int resultado = vehiculoDao.deleteVehicle(idVehiculo);
			if(resultado==1) {
				return "redirect:/misvehiculos?dok";
			}else {
				return "redirect:/misvehiculos?dnok";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
		
		
		
	}
}
