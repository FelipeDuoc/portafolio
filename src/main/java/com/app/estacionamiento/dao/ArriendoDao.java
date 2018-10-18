package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Arriendo;

@Component
public interface ArriendoDao {
	public void setDataSource(DataSource ds);
	public Arriendo newArriendo(Arriendo arriendo);
	public Arriendo arriendoActivo(Integer idPersona);
	public Arriendo finishArriendo(Integer idArriendo);
	public List<Arriendo> HistoricoDuenoParking(Integer idPersona);
	public Integer newCalification(Integer idArriendo, Integer idPersona, Integer puntaje, String comentario);

}
