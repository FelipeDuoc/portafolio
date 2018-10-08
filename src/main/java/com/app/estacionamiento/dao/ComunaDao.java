package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Comuna;

@Component
public interface ComunaDao {
	public List<Comuna> getAllComunes();
	public void setDataSource(DataSource ds);
}
