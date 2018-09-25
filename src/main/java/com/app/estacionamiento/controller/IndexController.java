package com.app.estacionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.estacionamiento.dao.TestDao;
import com.app.estacionamiento.domain.Test;

@Controller
public class IndexController {
	
	@Autowired
	private TestDao testDao;
	
	@GetMapping(value="/test")
	private ModelAndView IndexCon() {
		
		
		ModelAndView myv = new ModelAndView();
		
		List<Test> lista = testDao.getAllTest();
		myv.addObject("ListaExample", lista);
		
		myv.setViewName("inicio");;
		return myv;
	}
	
	@GetMapping(value="/search")
	private ModelAndView search() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("searchLatLon");;
		return myv;
	}
	
	@GetMapping(value="/index")
	private ModelAndView indexPage() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("index");;
		return myv;
	}
	
	@GetMapping(value="/iniciosesion")
	private ModelAndView inicioSesionPage() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("iniciosesion");;
		return myv;
	}
	
	@GetMapping(value="/registro")
	private ModelAndView Registro() {
		ModelAndView myv = new ModelAndView();
		
		myv.setViewName("registro");;
		return myv;
	}
}
