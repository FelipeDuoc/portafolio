package com.app.estacionamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.EstacionamientoObjBD;

@RestController
public class EstacionamientoControllerRest {
	
	@Autowired
	private EstacionamientoDao estacionamientoDao;
	
	@GetMapping(value="/estacionamientosDisponibles/get")
	private String verEstacionamientosDisponibles() {
		
		return null;
	}
}
