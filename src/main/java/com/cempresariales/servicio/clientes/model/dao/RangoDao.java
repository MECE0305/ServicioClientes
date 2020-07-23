package com.cempresariales.servicio.clientes.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

public interface RangoDao extends JpaRepository<RangoDesempenio, Long> {

	@Query("select r from RangoDesempenio r where ?1 between r.minimoRango and r.maximoRango and r.empresa.idEmpresa = ?2 and r.activoRango = true")
	public RangoDesempenio findByRangoAndEmpresa(@Param("rango") Double rango, @Param("idEmpresa") Long idEmpresa);

}
