package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArriendoController {
	
	@PostMapping(value="/arrendar")
	public String formularioArrendar(HttpSession sesion, Model model,
									@RequestParam(name="idEstacionamiento",required=true) String idEstacionamiento,
									@RequestParam(name="valorTarifa",required=true) String valorTarifa) {
		
		return "arrendarEstacionamiento";
	}
}
