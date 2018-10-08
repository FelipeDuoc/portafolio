package com.app.estacionamiento.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public interface ArriendoDao {
	public void setDataSource(DataSource ds);
}
