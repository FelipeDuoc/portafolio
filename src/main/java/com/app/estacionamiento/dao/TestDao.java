package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.app.estacionamiento.domain.Test;

@Component
public interface TestDao  {
	public void setDataSource(DataSource ds);
	public List<Test> getAllTest();
}
