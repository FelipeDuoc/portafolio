package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.estacionamiento.dao.VehiculoDao;
import com.app.estacionamiento.domain.Vehiculo;

@Controller
public class VehiculoController {
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@GetMapping(value="/misvehiculos")
	private String vehiculosview(HttpSession sesion, Model model) {
		int id_usuario;
		if(sesion.getAttribute("rol")!=null) {
			id_usuario =Integer.parseInt((String) sesion.getAttribute("persona"));
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			
			List<Vehiculo> lista = vehiculoDao.getAllVehicles(id_usuario);
			model.addAttribute("lista",lista);
			
			return "listavehiculos";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@GetMapping(value="/nuevovehiculo")
	private String vehiculosNewPage(HttpSession sesion, Model model) {
		if(sesion.getAttribute("rol")!=null) {
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "nuevovehiculo";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
}
