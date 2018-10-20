package com.app.estacionamiento.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.SesionDao;
import com.app.estacionamiento.domain.Calificacion;
import com.app.estacionamiento.domain.Credenciales;
import com.app.estacionamiento.domain.CredencialesResp;
import com.app.estacionamiento.rowmapper.CredencialesRespRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class SesionDaoImpl implements SesionDao{

	@Autowired
	DataSource datasource;
		
	@Override
	public CredencialesResp inicioSesion(Credenciales credenciales) {
		CredencialesResp cr = new CredencialesResp();
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_SESION.PROC_INICIO_SESION");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {
									new SqlParameter("in_usuario", OracleTypes.VARCHAR),
									new SqlParameter("in_pass", OracleTypes.VARCHAR),
									new SqlOutParameter("o_id_usuario",OracleTypes.VARCHAR),
									new SqlOutParameter("o_id_persona",OracleTypes.VARCHAR),
									new SqlOutParameter("o_id_rol",OracleTypes.VARCHAR),
									new SqlOutParameter("o_nombre_persona",OracleTypes.VARCHAR),
									};
        
		procedure.setParameters(parameters);
		procedure.compile();
		
		Map<String, Object>  result = procedure.execute(credenciales.getUser(),credenciales.getPass());
		
		if((String) result.get("o_id_persona")==null) {
			cr = null;
		}else {
			cr.setIdPersona((String) result.get("o_id_persona"));
			cr.setIdUsuario((String) result.get("o_id_usuario"));
			cr.setIdRol((String) result.get("o_id_rol"));
			cr.setNombrePersona((String) result.get("o_nombre_persona"));
		}
		
		return cr;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calificacion getCalification(Integer idPersona) {
		
		Calificacion cal = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_CALIFICACION.PROC_CALCULA_CALIFICACION");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {
									new SqlParameter("IN_ID_PERSONA", OracleTypes.INTEGER),
									new SqlOutParameter("O_PROMEDIO_CLIENTE",OracleTypes.VARCHAR),
									new SqlOutParameter("O_PROMEDIO_DUENO",OracleTypes.VARCHAR),
									new SqlOutParameter("O_RESULT",OracleTypes.INTEGER),
									};
        
		procedure.setParameters(parameters);
		procedure.compile();
		
		Map<String, Object>  result = procedure.execute(idPersona);
		
		Integer res = (Integer) result.get("O_RESULT");
		
		if(res.equals(1)) {
			cal = new Calificacion();
			cal.setPromedio_cliente((String) result.get("O_PROMEDIO_CLIENTE"));
			cal.setPromedio_dueno((String) result.get("O_PROMEDIO_DUENO"));
		}
		return cal;
		
	}

}
