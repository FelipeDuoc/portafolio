package com.app.estacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.app.estacionamiento.dao.TestDao;
import com.app.estacionamiento.domain.Test;

@RestController
public class IndexControllerRest {

	@Autowired
	private TestDao testDao;
	
	@GetMapping(value="/test/callajax")
	private String callAjax() {
		
		List<Test> lista = testDao.getAllTest();
		
		Gson gson = new Gson();
		String str = gson.toJson(lista);
		
		return str;
	}
}
