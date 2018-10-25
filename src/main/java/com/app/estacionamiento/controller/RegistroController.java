package com.app.estacionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.estacionamiento.dao.RegistroDao;
import com.app.estacionamiento.domain.Registro;
import com.app.estacionamiento.domain.Tarjeta;
import com.app.estacionamiento.domain.webservice.Response;

@Controller
public class RegistroController {
	
	@Autowired
	private RegistroDao registroDao;
	
	@GetMapping(value="/registro")
	private String Registro(Model model, 
							@RequestParam(name="ok",required=false) String ok,
							@RequestParam(name="nok",required=false) String nok,
							@RequestParam(name="nokcard",required=false) String nokcard) {
		
		model.addAttribute("ok",ok);
		model.addAttribute("nok", nok);
		model.addAttribute("nokcard", nokcard);
		model.addAttribute("registro", new Registro());
		return "registro";
	}
	
	@GetMapping(value="/micuenta")
	private String miCuenta(HttpSession sesion,
							Model model, 
							@RequestParam(name="nop",required=false) String nop,
							@RequestParam(name="nok",required=false) String nok,
							@RequestParam(name="ok",required=false) String ok,
							@RequestParam(name="tok",required=false) String tok,
							@RequestParam(name="tnok",required=false) String tnok,
							@RequestParam(name="nokcard",required=false) String nokcard,
							@RequestParam(name="nokc",required=false) String nokc,
							@RequestParam(name="nokd",required=false) String nokd,
							@RequestParam(name="nokcd",required=false) String nokcd) {
		
		if(sesion.getAttribute("rol")!=null) {
			int idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			int idUsuario =Integer.parseInt((String) sesion.getAttribute("usuario"));
			
			Registro reg = registroDao.myAccount(idPersona, idUsuario);
			
			
			if(reg==null) {
				model.addAttribute("tarjeta", "Sin información");
				model.addAttribute("cvv","");
				model.addAttribute("fecha","");
				model.addAttribute("registro", new Registro());
			}else {
				if(reg.getNumeroTarjeta()==null) {
					model.addAttribute("tarjeta", "Sin información");
					model.addAttribute("cvv","");
					model.addAttribute("fecha","");
				}else {
					model.addAttribute("tarjeta", "**** **** **** "+reg.getNumeroTarjeta().substring(12).toString());
					model.addAttribute("cvv","***");
					model.addAttribute("fecha","**/**");
				}
				
				if(reg.getNumeroCuentaDeposito()==null) {
					model.addAttribute("numeroCuentaDeposito", "Sin información");
					model.addAttribute("descripcionBanco","");
					model.addAttribute("descripcionCuenta","");
				}else {
					model.addAttribute("numeroCuentaDeposito", reg.getNumeroCuentaDeposito());
					model.addAttribute("descripcionBanco",reg.getBancoDescripcion());
					model.addAttribute("descripcionCuenta",reg.getTipoCuentaDescripcion());
				}
				
				model.addAttribute("registro", reg);
			}
			
			
			model.addAttribute("nok",nok);
			model.addAttribute("tnok", tnok);
			model.addAttribute("tok", tok);
			model.addAttribute("ok", ok);
			model.addAttribute("nokcard", nokcard);
			model.addAttribute("nokc", nokc);
			model.addAttribute("nokd", nokd);
			model.addAttribute("nokcd", nokcd);
			
			
			return "micuenta";
		}else {
			return "redirect:/iniciosesion?nop";
		}
	}
	
	@GetMapping(value="/editarinfopago")
	private String miInformacióDePago(HttpSession sesion,
							Model model, 
							@RequestParam(name="nop",required=false) String nop,
							@RequestParam(name="nok",required=false) String nok) {
		
		if(sesion.getAttribute("rol")!=null) {
			
			model.addAttribute("Tarjeta", new Tarjeta());
			return "miinfopago";
		}else {
			return "redirect:/iniciosesion?nop";
		}
		
	}
	
	@PostMapping(value="/registrar/registro")
	private String RegistroNuevo(Model model, @ModelAttribute("registro") Registro registro ) {

		if(registro.getIdRol()==2 || registro.getIdRol() == 5) {
			Boolean checkCard = registroDao.checkCreditCard(registro.getNumeroTarjeta());
			if(!checkCard) {
				return "redirect:/registro?nokcard";
			}else {
				Integer resultado = registroDao.registerAccount(registro);
				if(resultado ==1) {
					return "redirect:/registro?ok";
				}else {
					return "redirect:/registro?nok";
				}
			}
		}else {
			Integer resultado = registroDao.registerAccount(registro);
			if(resultado ==1) {
				return "redirect:/registro?ok";
			}else {
				return "redirect:/registro?nok";
			}
		}
		
	}
	
	@PostMapping(value="/micuenta/editatarjeta")
	private String EditaTarjeta(HttpSession sesion, 
								Model model, 
								@ModelAttribute("Tarjeta") Tarjeta tarjeta ) {
		
		if(sesion.getAttribute("rol")!=null) {
			
			Boolean checkCard = registroDao.checkCreditCard(tarjeta.getNumeroTarjeta());
			
			if(!checkCard) {
				return "redirect:/micuenta?nokcard";
			}else {
			
				Integer idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
				Integer resp = registroDao.setCreditCard(idPersona, tarjeta);
				
				if(resp.equals(1)) {
					return "redirect:/micuenta?tok";
				}else {
					return "redirect:/micuenta?tnok";
				}
			}
		}else{
			return "redirect:/iniciosesion?nop";
		}
	}
	
	@PostMapping(value="/micuenta/editacuenta")
	private String EditaCuenta(HttpSession sesion, 
								Model model, 
								@ModelAttribute("registro") Registro registro ) {
		
		if(sesion.getAttribute("rol")!=null) {
			Integer resp = 0;
			Integer idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			Integer idUsuario =Integer.parseInt((String) sesion.getAttribute("usuario"));
			Integer idRol =Integer.parseInt((String) sesion.getAttribute("rol"));
			
			if(idRol!=registro.getIdRol()) {
				
				switch(registro.getIdRol()) {
					case 2:
						if(registro.getNumeroTarjeta()!=null) {
							resp = registroDao.setMyAccount(idPersona, idUsuario, registro);
						}else {
							return "redirect:/micuenta?nokc";
						}
					break;
					case 3:
						if(registro.getNumeroCuentaDeposito()!=null) {
							resp = registroDao.setMyAccount(idPersona, idUsuario, registro);
						}else {
							return "redirect:/micuenta?nokd";
						}
					break;
					case 5:
						if(registro.getNumeroCuentaDeposito()!=null && registro.getNumeroTarjeta()!=null) {
							resp = registroDao.setMyAccount(idPersona, idUsuario, registro);
						}else {
							return "redirect:/micuenta?nokcd";
						}
					break;
				
				}
			}else {
				resp = registroDao.setMyAccount(idPersona, idUsuario, registro);
			}

			if(resp.equals(1)) {
				return "redirect:/micuenta?ok";
			}else {
				return "redirect:/micuenta?nok";
			}
		}else{
			return "redirect:/iniciosesion?nop";
		}
	}

}
