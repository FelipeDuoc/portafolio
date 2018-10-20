package com.app.estacionamiento.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.estacionamiento.dao.RegistroDao;
import com.app.estacionamiento.domain.Registro;
import com.app.estacionamiento.domain.Tarjeta;
import com.app.estacionamiento.domain.webservice.Response;

import oracle.jdbc.OracleTypes;

@Component
public class RegistroDaoImpl implements RegistroDao{

	@Autowired
	DataSource datasource;
	
	@Override
	public int registerAccount(Registro registro) {
		int res=0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_REGISTRO.PROC_REGISTRO_CLIENTE");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_RUT_PERSONA",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRES_PERSONA",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_PATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_MATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CAllE",OracleTypes.VARCHAR),
										new SqlParameter("IN_TELEFONO",OracleTypes.VARCHAR),
										new SqlParameter("IN_EMAIL",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_CODIGO_SEGURIDAD_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_FECHA_VENCIMIENTO_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_USUARIO",OracleTypes.VARCHAR),
										new SqlParameter("IN_PASSWORD_USUARIO",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_ROL",OracleTypes.INTEGER),
										
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(registro.getRut(),
														registro.getNombre(),
														registro.getApellidoPaterno(),
														registro.getApellidoMaterno(),
														registro.getNombreCalle(),
														registro.getNumeroCalle(),
														registro.getTelefono(),
														registro.getEmail(),
														registro.getNumeroTarjeta(),
														registro.getCodigoSeguridadTarjeta(),
														registro.getFechaVencimiento(),
														registro.getUsuario(),
														registro.getContrasena(),
														registro.getIdRol());
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registro myAccount(int idPersona, int idUsuario) {
		Registro reg = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_REGISTRO.PROC_SELECT_CLIENTE_XID");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_USUARIO",OracleTypes.INTEGER),
										new SqlOutParameter("O_RUT",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NOMBRES",OracleTypes.VARCHAR),
										new SqlOutParameter("O_APELLIDO_PATERNO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_APELLIDO_MATERNO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NUMERO_CALLE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_TELEFONO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_EMAIL",OracleTypes.VARCHAR),
										new SqlOutParameter("O_NOMBRE_USUARIO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_ROL_ID",OracleTypes.INTEGER),
										new SqlOutParameter("O_ID_TARJETA_CREDITO",OracleTypes.INTEGER),
										new SqlOutParameter("O_NUMERO_TARJETA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_CODIGO_SEGURIDAD",OracleTypes.VARCHAR),
										new SqlOutParameter("O_FECHA_VENCIMIENTO",OracleTypes.VARCHAR),
										
										new SqlOutParameter("O_NUMERO_CUENTA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_ID_BANCO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_BANCO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_ID_TIPO_CUENTA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_TIPO_CUENTA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_PASS",OracleTypes.VARCHAR),
				
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)};
		procedure.setParameters(parameters);
		procedure.compile();
		
		Map<String, Object>  result = procedure.execute(idPersona, idUsuario);
		
		Integer res = (Integer) result.get("O_RESULT");
		
		if(res.equals(1)) {
			reg = new Registro();
			reg.setRut((String) result.get("O_RUT"));
			reg.setNombre((String) result.get("O_NOMBRES"));
			reg.setApellidoPaterno((String) result.get("O_APELLIDO_PATERNO"));
			reg.setApellidoMaterno((String) result.get("O_APELLIDO_MATERNO"));
			reg.setNombreCalle((String) result.get("O_NOMBRE_CALLE"));
			reg.setNumeroCalle((String) result.get("O_NUMERO_CALLE"));
			reg.setTelefono((String) result.get("O_TELEFONO"));
			reg.setEmail((String) result.get("O_EMAIL"));
			reg.setUsuario((String) result.get("O_NOMBRE_USUARIO"));
			reg.setIdRol((Integer) result.get("O_ROL_ID"));
			reg.setIdTarjeta((Integer) result.get("O_ID_TARJETA_CREDITO"));
			reg.setNumeroTarjeta((String) result.get("O_NUMERO_TARJETA"));
			reg.setCodigoSeguridadTarjeta((String) result.get("O_CODIGO_SEGURIDAD"));
			reg.setFechaVencimiento((String) result.get("O_FECHA_VENCIMIENTO"));
			reg.setNumeroCuentaDeposito((String) result.get("O_NUMERO_CUENTA"));
			reg.setIdBanco((String) result.get("O_ID_BANCO"));
			reg.setBancoDescripcion((String) result.get("O_BANCO"));
			reg.setIdTipoCuenta((String) result.get("O_ID_TIPO_CUENTA"));
			reg.setTipoCuentaDescripcion((String) result.get("O_TIPO_CUENTA"));
			reg.setContrasena((String) result.get("O_PASS"));
		}
		
		return reg;
	}

	@Override
	public Integer setCreditCard(Integer idPersona, Tarjeta tarjeta) {
		Integer resp = 0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_REGISTRO.PROC_UPDATE_TARJETA_CREDITO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_NUMERO_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_CODIGO_SEGURIDAD_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_FECHA_VENCIMIENTO_TARJETA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)};
		
		procedure.setParameters(parameters);
		procedure.compile();
		
		Map<String, Object>  result = procedure.execute(idPersona,
														tarjeta.getNumeroTarjeta(),
														tarjeta.getFechaExpiracion(),
														tarjeta.getCodigoSeguridad());
		
		resp = (Integer) result.get("O_RESULT");
		
		return resp;
	}

	@Override
	public Integer setMyAccount(Integer idPersona, Integer idUsuario, Registro registro) {
		Integer resp = 0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_REGISTRO.PROC_UPDATE_CLIENTE");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_NOMBRES_PERSONA",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_PATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_MATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CAllE",OracleTypes.VARCHAR),
										new SqlParameter("IN_TELEFONO",OracleTypes.VARCHAR),
										new SqlParameter("IN_EMAIL",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_USUARIO",OracleTypes.VARCHAR),
										new SqlParameter("IN_PASSWORD_USUARIO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)};
		
		procedure.setParameters(parameters);
		procedure.compile();
		
		Map<String, Object>  result = procedure.execute(idPersona,
														registro.getNombre(),
														registro.getApellidoPaterno(),
														registro.getApellidoMaterno(),
														registro.getNombreCalle(),
														registro.getNumeroCalle(),
														registro.getTelefono(),
														registro.getEmail(),
														idUsuario,
														registro.getContrasena());
		
		resp = (Integer) result.get("O_RESULT");
		
		return resp;
	}

	@Override
	public Boolean checkCreditCard(String creditCardNumber) {
		Boolean result;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://wscheckcard.herokuapp.com/checkid")
				   .queryParam("idCard", creditCardNumber);

		RestTemplate restTemplate = new RestTemplate();
		Response resp = restTemplate.getForObject(builder.toUriString(), Response.class);
		
		result = resp.getIsvalid();
		
		return result;
		
		
	}

}
