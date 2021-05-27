package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezado;
import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezadoPK;

public interface EvaluacionEncabezadoDao extends JpaRepository<EvaluacionHasEncabezado, EvaluacionHasEncabezadoPK> {

	@Query("select ee from EvaluacionHasEncabezado ee where ee.encabezado.idEncabezado = ?1")
	public List<EvaluacionHasEncabezado> findByEncabezado(@Param("idEncabezado") Long id);

	@Query("select ee from EvaluacionHasEncabezado ee where ee.evaluacion.idEvaluacion= ?1")
	public List<EvaluacionHasEncabezado> findByEvaluacion(@Param("idEvaluacion") Long id);
}
