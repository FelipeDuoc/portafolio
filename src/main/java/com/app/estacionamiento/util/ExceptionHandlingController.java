package com.app.estacionamiento.util;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView serviceError(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mensaje","ups, ha ocurrido un error en la transacción, intenta nuevamente");
		mav.setViewName("error2");
		return mav;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ModelAndView serviceHandle(Exception exception) {
		String m = exception.getLocalizedMessage();
		ModelAndView mav = new ModelAndView();
		mav.addObject("mensaje","ups, ha ocurrido un error en la transacción, intenta nuevamente");
		mav.setViewName("error2");
		return mav;
	}
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView serviceException(Exception exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("mensaje","ups, ha ocurrido un error en la transacción, intenta nuevamente");
		mav.setViewName("error2");
		return mav;
	}
	
}
