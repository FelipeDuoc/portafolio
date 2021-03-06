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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.domain.Arriendo;
import com.app.estacionamiento.domain.webservice.Response;
import com.app.estacionamiento.rowmapper.ArriendoPendientesRowMapper;
import com.app.estacionamiento.rowmapper.ArriendoRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class ArriendoDaoImpl implements ArriendoDao {

	
	@Autowired
	DataSource datasource;	
	
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Arriendo newArriendo(Arriendo arriendo) {
		Integer res = 0;
		Integer res_total = 0;
		Arriendo arr = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_INICIO_ARRIENDO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_FECHA_ENTRADA",OracleTypes.TIMESTAMP),
										new SqlParameter("IN_FECHA_SALIDA_ESTIMADA",OracleTypes.TIMESTAMP),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_VEHICULO",OracleTypes.INTEGER),
										new SqlOutParameter("O_FECHA_INICIO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_FECHA_TERMINO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_PATENTE_VEHICULO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_DUENO_EST",OracleTypes.VARCHAR),
										new SqlOutParameter("O_CONTACTO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_DIRECCION_EST",OracleTypes.VARCHAR),
										
										new SqlOutParameter("O_TOTAL_PAGO",OracleTypes.INTEGER),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(arriendo.getFechaDesde(),
														arriendo.getFechaHasta(),
														arriendo.getIdPersona(),
														arriendo.getIdEstacionamiento(),
														arriendo.getIdVehiculo());
		res = (Integer) result.get("O_RESULT");
		res_total = (Integer) result.get("O_TOTAL_PAGO");
		
		
		if(res.equals(1)) {
			arr = new Arriendo();
			arr = arriendo;
			arr.setTotalArriendo(res_total);
			arr.setPatenteVehiculo((String) result.get("O_PATENTE_VEHICULO"));
			arr.setNombreDuenoEstacionamiento((String) result.get("O_DUENO_EST"));
			arr.setTelefonoDueno((String) result.get("O_CONTACTO"));
			arr.setDireccionEstacionamiento((String) result.get("O_DIRECCION_EST"));
			return arr;
		}else {
			return arr;
		}
		
	}

	@Override
	public Arriendo arriendoActivo(Integer idPersona) {
		Integer res = 0;
		Arriendo arr = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_SELECT_ARRIENDO_ACTIVO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("O_ID_ARRIENDO",OracleTypes.INTEGER),
										new SqlOutParameter("O_FECHA_ENTRADA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_FECHA_SALIDA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_TARIFA",OracleTypes.VARCHAR),
										new SqlOutParameter("O_PATENTE",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		res = (Integer) result.get("O_RESULT");
		Integer idArriendo = (Integer) result.get("O_ID_ARRIENDO");
		
		
		if(res.equals(1) && idArriendo!=null) {
			arr = new Arriendo();
			arr.setIdArriendo((Integer) result.get("O_ID_ARRIENDO"));
			arr.setFechaDesde((String) result.get("O_FECHA_ENTRADA"));
			arr.setFechaHasta((String) result.get("O_FECHA_SALIDA"));
			arr.setValorTarifa((String) result.get("O_TARIFA"));
			arr.setPatenteVehiculo((String) result.get("O_PATENTE"));
			return arr;
		}else {
			return arr;
		}
	}

	@Override
	public Arriendo finishArriendo(Integer idArriendo) {
		Integer res = 0;
		Arriendo arr = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_TERMINO_ARRIENDO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("O_VALOR_PAGO",OracleTypes.VARCHAR),
										new SqlOutParameter("O_FECHA_SALIDA_REAL",OracleTypes.VARCHAR),
										new SqlOutParameter("O_MINUTOS_DIF",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idArriendo);
		res = (Integer) result.get("O_RESULT");
		String pago = (String) result.get("O_VALOR_PAGO");
		String fechaSalida = (String) result.get("O_FECHA_SALIDA_REAL");
		String minutosDiff = (String) result.get("O_MINUTOS_DIF");
		
		Integer pagoInt = Integer.parseInt(pago);
		
		if(res.equals(1)) {
			arr = new Arriendo();
			arr.setTotalPagoExtra(pagoInt);
			arr.setFechaRealSalida(fechaSalida);
			arr.setTiempoDiferencia(minutosDiff);
			
			return arr;
		}else {
			return arr;
		}
	}

	@Override
	public List<Arriendo> HistoricoDuenoParking(Integer idPersona) {
		List<Arriendo> lista = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_SELECT_HISTORICO_DUENO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new ArriendoRowMapper()),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		lista = (List<Arriendo>) result.get("PCURSOR");
		Integer res = (Integer) result.get("O_RESULT");
		
		if(res.equals(1)) {
			return lista;
		}else {
			return lista = null;
		}
	}

	@Override
	public Integer newCalification(Integer idArriendo, Integer idPersona, Integer puntaje, String comentario) {
		Integer resp = 0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_CALIFICACION.PROC_INSERT_CALIFICACION");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_ARRIENDO",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_PUNTAJE",OracleTypes.INTEGER),
										new SqlParameter("IN_COMENTARIO",OracleTypes.VARCHAR),
										
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idArriendo,idPersona,puntaje,comentario);
		resp = (Integer) result.get("O_RESULT");
		return resp;
	}

	@Override
	public List<Arriendo> PendientesCalificacion(Integer idPersona, String idRolCalificador) {
		List<Arriendo> lista = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_CALIFICACION.PROC_SELECT_ARRIENDO_NO_CALIF");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_ROL_CALIFICACION",OracleTypes.VARCHAR),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new ArriendoPendientesRowMapper())
										
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona, idRolCalificador);
		lista = (List<Arriendo>) result.get("PCURSOR");
		Integer res = (Integer) result.get("O_RESULT");
		
		if(res.equals(1)) {
			return lista;
		}else {
			return lista = null;
		}
	}

	@Override
	public List<Arriendo> HistoricoClienteParking(Integer idPersona) {
		List<Arriendo> lista = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_SELECT_HISTORICO_CLIENTE");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new ArriendoRowMapper()),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		lista = (List<Arriendo>) result.get("PCURSOR");
		Integer res = (Integer) result.get("O_RESULT");
		
		if(res.equals(1)) {
			return lista;
		}else {
			return lista = null;
		}
	}

	@Override
	public Integer checkCredits(Integer idPersona, Integer idEstacionamiento, String fechaDesde, String fechaHasta) {
		Integer result;
//		fechaDesde = fechaDesde.replace(" ", "%20");
//		fechaHasta = fechaHasta.replace(" ", "%20");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://wscheckcredit.herokuapp.com/checkcredits")
				   .queryParam("idPersona", idPersona)
				   .queryParam("idEstacionamiento", idEstacionamiento)
				   .queryParam("fechaDesde", fechaDesde)
				   .queryParam("fechaHasta", fechaHasta);

		RestTemplate restTemplate = new RestTemplate();
		result = restTemplate.getForObject(builder.toUriString(), Integer.class);
		
		return result;
	}

}
