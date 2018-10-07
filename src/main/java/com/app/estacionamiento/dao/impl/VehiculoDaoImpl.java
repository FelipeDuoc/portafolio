package com.app.estacionamiento.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.estacionamiento.dao.VehiculoDao;
import com.app.estacionamiento.domain.Vehiculo;
import com.app.estacionamiento.rowmapper.VehiculoRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class VehiculoDaoImpl implements VehiculoDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> getAllVehicles(int id_usuario) {
		List<Vehiculo> resp= null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_SELECT_VEHICULOS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("in_id_persona",OracleTypes.INTEGER),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new VehiculoRowMapper()),
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(id_usuario);
		resp = (ArrayList<Vehiculo>) result.get("PCURSOR");
		return resp;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int createVehicle(Vehiculo vehiculo,int idPersona) {
		int res = 0;
		String patente = vehiculo.getPatente();
		String descripcion = vehiculo.getDescripcion();
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_INSERT_VEHICULOS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_PATENTE",OracleTypes.VARCHAR),
										new SqlParameter("IN_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(patente,descripcion,idPersona);
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public Vehiculo getVehiculexId(int idPersona, int idVehiculo) {
		Vehiculo veh = null;
		String res = "";
		int res_id_vehiculo;
		String res_patente = null;
		String res_descripcion = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_SELECT_VEHICULOS_XID");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("in_id_persona",OracleTypes.INTEGER),
										new SqlParameter("in_id_vehiculo",OracleTypes.INTEGER),
										new SqlOutParameter("O_ID_VEHICULO",OracleTypes.INTEGER),
										new SqlOutParameter("O_PATENTE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona,idVehiculo);
		res = (String) result.get("o_sql_code");
		res_id_vehiculo = (int) result.get("O_ID_VEHICULO");
		res_patente = (String) result.get("O_PATENTE");
		res_descripcion = (String) result.get("O_DESCRIPCION");

		if(res.equals("1")) {
			veh = new Vehiculo();
			veh.setIdVehiculo(res_id_vehiculo);
			veh.setPatente(res_patente);
			veh.setDescripcion(res_descripcion);
		}else {
			return veh;
		}
		
		return veh;
		
	}

	@Override
	public int updateVehicle(Vehiculo vehiculo) {
		int res = 0;
		String patente = vehiculo.getPatente();
		String descripcion = vehiculo.getDescripcion();
		int idVehiculo = vehiculo.getIdVehiculo();
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_UPDATE_VEHICULOS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_VEHICULO",OracleTypes.INTEGER),
										new SqlParameter("IN_PATENTE",OracleTypes.VARCHAR),
										new SqlParameter("IN_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idVehiculo,patente,descripcion);
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public int deleteVehicle(int idVehiculo) {
		int res = 0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_DELETE_VEHICULOS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_VEHICULO",OracleTypes.INTEGER),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idVehiculo);
		res = (int) result.get("O_RESULT");
		
		return res;
	}
	
}
