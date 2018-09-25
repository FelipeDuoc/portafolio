package com.app.estacionamiento.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.TestDao;
import com.app.estacionamiento.domain.Test;
import com.app.estacionamiento.rowmapper.TestRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class TestDaoImpl implements TestDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAllTest() {
		List<Test> resp = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_TEST.PROC_SELECT_TEST");
		procedure.setFunction(false);
		
		SqlOutParameter parameters = new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new TestRowMapper());
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute();
		resp = (ArrayList<Test>) result.get("PCURSOR");
		
		return resp;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

}
