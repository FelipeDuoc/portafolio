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

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.Estacionamiento;
import com.app.estacionamiento.domain.EstacionamientoObjBD;
import com.app.estacionamiento.domain.Vehiculo;

@Controller
public class EstacionamientoController {
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
	@GetMapping(value="/misestacionamientos")
	private String EstacionamientoList(HttpSession sesion, Model model,
									@RequestParam(name="dok",required=false) String dok,
									@RequestParam(name="dnok",required=false) String dnok,
									@RequestParam(name="uok",required=false) String uok,
									@RequestParam(name="unok",required=false) String unok) {
		if(sesion.getAttribute("rol")!=null) {
			int idPersona;
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			List<Estacionamiento> lista = estacionamientoDao.getAllParking(idPersona);
			model.addAttribute("dok",dok);
			model.addAttribute("dnok",dnok);
			model.addAttribute("uok",uok);
			model.addAttribute("unok",unok);
			model.addAttribute("lista",lista);
			
			return "listaestacionamientos";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@GetMapping(value="/nuevoestacionamiento")
	private String nuevoEstacionamiento(HttpSession sesion, 
										Model model,
										@RequestParam(name="OK",required=false) String ok,
										@RequestParam(name="NOK",required=false) String nok) {
		if(sesion.getAttribute("rol")!=null) {
			model.addAttribute("ok",ok);
			model.addAttribute("nok",nok);
			
			Integer tar = estacionamientoDao.selectTipoTarifaCurrent();
			EstacionamientoObjBD est = new EstacionamientoObjBD();
			
			if(tar==0) {
				model.addAttribute("estacionamiento", est);
			}else {
				est.setValorTarifa(tar);
				model.addAttribute("estacionamiento",est);
			}
			
			return "nuevoestacionamiento";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	//controlador hibrido
	@RequestMapping(value="/actualizaestacionamiento", method = { RequestMethod.POST, RequestMethod.GET })
	private String EstacionamientoUpdatePage(HttpSession sesion, 
									Model model, 
									@RequestParam(name="OK",required=false) String ok,
									@RequestParam(name="NOK",required=false) String nok,
									@RequestParam(name="mod",required=false) String mod,
									@RequestParam(name="idEstacionamiento",required=true) int idEstacionamiento) {
		
		if(sesion.getAttribute("rol")!=null) {
			int idPersona;
			EstacionamientoObjBD estacionamiento = new EstacionamientoObjBD();
			
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			
			Integer tar = estacionamientoDao.selectTipoTarifaCurrent();
			
			estacionamiento = estacionamientoDao.getParkingById(idPersona, idEstacionamiento);
			
			if(tar==0) {
				model.addAttribute("estacionamiento", estacionamiento);
			}else {
				estacionamiento.setValorTarifa(tar);
				model.addAttribute("estacionamiento",estacionamiento);
			}
			
			model.addAttribute("ok",ok);
			model.addAttribute("nok",nok);
			
			model.addAttribute("nombre",sesion.getAttribute("nombre").toString());
			return "actualizaestacionamiento";
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@PostMapping(value="/actualizaestacionamiento/actualiza")
	private String EstacionamientoUpdate(HttpSession sesion, Model model,@ModelAttribute("estacionamiento") EstacionamientoObjBD estacionamiento ) {
		if(sesion.getAttribute("rol")!=null) {
			int resultado= estacionamientoDao.updateParking(estacionamiento);
			if(resultado==1) {
//				return "redirect:/actualizaestacionamiento?OK&idEstacionamiento="+estacionamiento.getIdEstacionamiento();
				return "redirect:/misestacionamientos?uok";
			}else {
//				return "redirect:/actualizaestacionamiento?NOK&idEstacionamiento="+estacionamiento.getIdEstacionamiento();
				return "redirect:/misestacionamientos?unok";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	
	
	@PostMapping(value="/nuevoestacionamiento/crear")
	private String creaEstacionamiento(HttpSession sesion, Model model,@ModelAttribute("estacionamiento") EstacionamientoObjBD estacionamiento ) {
		int idPersona;
		if(sesion.getAttribute("rol")!=null) {
			idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			int resultado = estacionamientoDao.createParking(estacionamiento, idPersona);
			if(resultado==1) {
				return "redirect:/nuevoestacionamiento?OK";
			}else {
				return "redirect:/nuevoestacionamiento?NOK";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	@PostMapping(value="/eliminaestacionamiento/elimina")
	private String deleteVehiculo(HttpSession sesion, Model model, int idEstacionamiento){
		if(sesion.getAttribute("rol")!=null) {
			int resultado = estacionamientoDao.deleteParking(idEstacionamiento);
			if(resultado==1) {
				return "redirect:/misestacionamientos?dok";
			}else {
				return "redirect:/misestacionamientos?dnok";
			}
			
		}else {
			return "redirect:/iniciosesion";
		}
	}
	
	
}
