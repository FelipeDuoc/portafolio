package com.app.estacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.EstacionamientoObjBD;
import com.google.gson.Gson;

@RestController
public class EstacionamientoControllerRest {
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
	@GetMapping(value="/estacionamientosDisponibles/get")
	private String verEstacionamientosDisponibles(@RequestParam(value="nombreComuna", required=false) String nombreComuna) {
		
		List<EstacionamientoObjBD> lista = estacionamientoDao.getAllParkinginAvailable(nombreComuna);
		
		Gson gson = new Gson();
		String str = gson.toJson(lista);
		
		return str;
	}
	
}
