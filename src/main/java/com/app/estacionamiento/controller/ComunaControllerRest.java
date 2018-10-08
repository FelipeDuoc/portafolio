package com.app.estacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estacionamiento.dao.ComunaDao;
import com.app.estacionamiento.domain.Comuna;
import com.google.gson.Gson;

@RestController
public class ComunaControllerRest {
	
	@Autowired
	private ComunaDao comunaDao;
	
	@GetMapping(value="/comunas/get")
	private String verComunas() {
		
		List<Comuna> lista = comunaDao.getAllComunes();
		
		Gson gson = new Gson();
		String str = gson.toJson(lista);
		
		return str;
	}
	
}
