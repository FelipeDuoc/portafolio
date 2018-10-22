package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Banco;
import com.app.estacionamiento.domain.Estacionamiento;
import com.app.estacionamiento.domain.EstacionamientoObjBD;
import com.app.estacionamiento.domain.TipoCuentaBanco;

@Component
public interface EstacionamientoDao {
	public List<Estacionamiento> getAllParking(int idPersona);
	public int createParking(EstacionamientoObjBD estacionamiento, int idPersona);
	public void setDataSource(DataSource ds);
	public List<EstacionamientoObjBD> getAllParkinginAvailable(String nombreComuna);
	public EstacionamientoObjBD getParkingById(int idPersona, int idEstacionamiento);
	public int updateParking(EstacionamientoObjBD estacionamiento);
	public int deleteParking(int idEstacionamiento);
	public List<EstacionamientoObjBD> getParkingInUse(Integer idPersona);
	public Integer selectTipoTarifaCurrent();
	public List<Banco> getAllBanks();
	public List<TipoCuentaBanco> getAllAccountTypes();
}
