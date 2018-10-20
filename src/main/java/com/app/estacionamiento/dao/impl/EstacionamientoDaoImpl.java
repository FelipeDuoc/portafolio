package com.app.estacionamiento.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.Estacionamiento;
import com.app.estacionamiento.domain.EstacionamientoObjBD;
import com.app.estacionamiento.rowmapper.EstacionamientoObjBDRowMapper;
import com.app.estacionamiento.rowmapper.EstacionamientoRowMapper;
import com.app.estacionamiento.rowmapper.EstacionamientosEnUsoRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class EstacionamientoDaoImpl implements EstacionamientoDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estacionamiento> getAllParking(int idPersona) {
		List<Estacionamiento> lista = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_SELECT_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("in_id_persona",OracleTypes.INTEGER),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new EstacionamientoRowMapper()),
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		lista = (List<Estacionamiento>) result.get("PCURSOR");
		
		return lista;
	}

	@Override
	public void setDataSource(DataSource ds) {
		
	}

	@Override
	public int createParking(EstacionamientoObjBD estacionamiento, int idPersona) {
		int res=0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_INSERT_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlParameter("IN_LATITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_LONGITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_OBSERVACION",OracleTypes.VARCHAR),
										new SqlParameter("IN_COMUNA",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_VALOR_TARIFA",OracleTypes.INTEGER),
										
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(estacionamiento.getDescripcion(),
														estacionamiento.getLatitud(),
														estacionamiento.getLongitud(),
														estacionamiento.getNombreCalle(),
														estacionamiento.getNumeroCalle(),
														estacionamiento.getObservacion(),
														estacionamiento.getNombreComuna(),
														idPersona,
														estacionamiento.getValorTarifa());
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstacionamientoObjBD> getAllParkinginAvailable(String nombreComuna) {
		List<EstacionamientoObjBD> lista = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_EST_DISPONIBLES");
		procedure.setFunction(false);
		
		SqlParameter[] parameters =  {	new SqlParameter("in_nombre_comuna",OracleTypes.VARCHAR),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new EstacionamientoObjBDRowMapper()),
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(nombreComuna);
		lista = (List<EstacionamientoObjBD>) result.get("PCURSOR");
		
		return lista;
	}

	@Override
	public EstacionamientoObjBD getParkingById(int idPersona, int idEstacionamiento) {
		EstacionamientoObjBD est = new EstacionamientoObjBD();
		String res;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_SELECT_EST_XID");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("O_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlOutParameter("O_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlOutParameter("O_LATITUD",OracleTypes.VARCHAR),
										new SqlOutParameter("O_LONGITUD",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NUMERO_CALLE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_DISPONIBILIDAD",OracleTypes.INTEGER),
										new SqlOutParameter("O_OBSERVACION",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NOMBRE_COMUNA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_ESTADO",OracleTypes.INTEGER),
										new SqlOutParameter("O_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("O_NOMBRE_DUENO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_TARIFA_ID",OracleTypes.INTEGER),
										new SqlOutParameter("O_VALOR_VARIFA",OracleTypes.INTEGER),
										new SqlOutParameter("O_DESCRIPCION_TIPO_TARIFA",OracleTypes.VARCHAR),
										
										
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idEstacionamiento,idPersona);
		res = (String) result.get("o_sql_code");
		
		if(res.equals("1")) {
			est.setIdEstacionamiento((int) result.get("O_ID_ESTACIONAMIENTO"));
			est.setDescripcion((String) result.get("O_DESCRIPCION"));
			est.setLatitud((String) result.get("O_LATITUD"));
			est.setLongitud((String) result.get("O_LONGITUD"));
			est.setNombreCalle((String) result.get("O_NOMBRE_CALLE"));
			est.setNumeroCalle((String) result.get("O_NUMERO_CALLE"));
			est.setDisponibilidad((int) result.get("O_DISPONIBILIDAD"));
			est.setObservacion((String) result.get("O_OBSERVACION"));
			est.setNombreComuna((String) result.get("O_NOMBRE_COMUNA"));
			est.setIdEstado((int) result.get("O_ESTADO"));
			est.setIdPersona((int) result.get("O_ID_PERSONA"));
			est.setNombreDueno((String) result.get("O_NOMBRE_DUENO"));
			est.setIdTarifa((int) result.get("O_TARIFA_ID"));
			est.setValorTarifa((int) result.get("O_VALOR_VARIFA"));
			est.setDescripcionTipoTarifa((String) result.get("O_DESCRIPCION_TIPO_TARIFA"));
			
		}else {
			
		}
		
		return est;
	}

	@Override
	public int updateParking(EstacionamientoObjBD estacionamiento) {
		int res = 0;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_UPDATE_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlParameter("IN_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlParameter("IN_LATITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_LONGITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_OBSERVACION",OracleTypes.VARCHAR),
										new SqlParameter("IN_COMUNA",OracleTypes.VARCHAR),
										new SqlParameter("IN_ESTADO",OracleTypes.INTEGER),
										new SqlParameter("IN_VALOR_TARIFA",OracleTypes.INTEGER),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(estacionamiento.getIdEstacionamiento(),
														estacionamiento.getDescripcion(),
														estacionamiento.getLatitud(),
														estacionamiento.getLongitud(),
														estacionamiento.getNombreCalle(),
														estacionamiento.getNumeroCalle(),
														estacionamiento.getObservacion(),
														estacionamiento.getNombreComuna(),
														estacionamiento.getIdEstado(),
														estacionamiento.getValorTarifa()
														);
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public int deleteParking(int idEstacionamiento) {
		int res = 0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_DELETE_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idEstacionamiento);
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public List<EstacionamientoObjBD> getParkingInUse(Integer idPersona) {
		List<EstacionamientoObjBD> lista = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_SELECT_EST_ARRENDADO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters =  {	new SqlParameter("IN_ID_PERSONA",OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR", OracleTypes.CURSOR, new EstacionamientosEnUsoRowMapper()),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		lista = (List<EstacionamientoObjBD>) result.get("PCURSOR");
		
		Integer resp = (Integer) result.get("O_RESULT");
		
		return lista;
	}

}
