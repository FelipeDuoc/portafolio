package com.app.estacionamiento.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.dao.VehiculoDao;
import com.app.estacionamiento.domain.Arriendo;
import com.app.estacionamiento.domain.Vehiculo;

@Controller
public class ArriendoController {
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Autowired
	private ArriendoDao arriendoDao;
	
	@PostMapping(value="/arrendar")
	public String formularioArrendar(HttpSession sesion, Model model,
									@RequestParam(name="idEstacionamiento",required=true) String idEstacionamiento,
									@RequestParam(name="valorTarifa",required=true) String valorTarifa) {
		if(sesion.getAttribute("rol")!=null) {
			int idPersona =Integer.parseInt((String) sesion.getAttribute("persona"));
			List<Vehiculo> listaVehiculos =  vehiculoDao.getAllVehicles(idPersona);
			
			model.addAttribute("valorTarifa", valorTarifa);
			model.addAttribute("idEstacionamiento", idEstacionamiento);
			model.addAttribute("listavehiculos", listaVehiculos);
			
			return "arrendarEstacionamiento";
		}else {
			return "redirect:/iniciosesion?nop";
		}
		
	}
	
	@PostMapping(value="/arrendarestacionamiento")
	public String arrendarEstacionamiento(HttpSession sesion, 
									      Model model,
									      @RequestParam(name="cantidadHoras",required=true) String cantidadHoras,
									      @RequestParam(name="cantidadMinutos",required=true) String cantidadMinutos,
									      @RequestParam(name="valorTarifa",required=true) String valorTarifa,
									      @RequestParam(name="idVehiculo", required=true) String idVehiculo,
									      @RequestParam(name="idEstacionamiento", required=true) String idEstacionamiento
									      ) {
		if(sesion.getAttribute("rol")!=null) {
			Arriendo arr = new Arriendo();
			
			arr.setIdPersona(Integer.parseInt((String) sesion.getAttribute("persona"))); 
			
			Calendar cal = Calendar.getInstance();
			arr.setFechaDesde(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
			
			cal.add(Calendar.HOUR,Integer.parseInt(cantidadHoras));
			cal.add(Calendar.MINUTE,Integer.parseInt(cantidadMinutos));
			
			arr.setFechaHasta(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
			arr.setIdVehiculo(Integer.parseInt(idVehiculo));
			arr.setIdEstacionamiento(Integer.parseInt(idEstacionamiento));
			
			Arriendo arrRes = arriendoDao.newArriendo(arr);
			
			if(arrRes!=null) {
				model.addAttribute("arriendoOK", arrRes);
				model.addAttribute("fechaDesde",arrRes.getFechaDesde());
				model.addAttribute("fechaHasta",arrRes.getFechaHasta());
				model.addAttribute("totalArriendo", arrRes.getTotalArriendo());
				model.addAttribute("vehiculo", arrRes.getPatenteVehiculo());
				model.addAttribute("nombreDueno", arrRes.getNombreDuenoEstacionamiento());
				model.addAttribute("telefonoDueno", arrRes.getTelefonoDueno());
				model.addAttribute("descripcionEstacionamiento", arrRes.getDireccionEstacionamiento());
				return "resumenarriendo";
			}else {
				
				return "redirect:/inicio?anok";
			}
		}else {
			return "redirect:/iniciosesion?nop";
		}
		
	}
	
	@GetMapping("/historicoarriendo")
	public String resumenArriend(Model model) {
		return "historicoarriendo";
	}
	
	
	@PostMapping("/finalizaarriendo")
	public String FinalizaArriendoPage( HttpSession sesion, 
										Model model, 
										@RequestParam(name="idArriendo",required=true) String idArriendo) {
		
		if(sesion.getAttribute("rol")!=null) {
			Integer arriendo = Integer.parseInt(idArriendo);
			Arriendo finish = arriendoDao.finishArriendo(arriendo);
			model.addAttribute("idArriendo", idArriendo);
			if(finish!=null) {
				model.addAttribute("fechasalida", finish.getFechaRealSalida());
				model.addAttribute("totalpagoextra", finish.getTotalPagoExtra());
				model.addAttribute("tiempodiferencia", finish.getTiempoDiferencia());
				return "finalizaarriendoresumen";
			}else {
				return "inicioArriendoActivo"; 
			}
			
		}else {
			return "redirect:/iniciosesion?nop";
		}
	}
}
