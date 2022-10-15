package com.cempresariales.servicio.clientes.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezado;
import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezadoPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluacionHasEncabezadoDao extends JpaRepository<EvaluacionHasEncabezado, EvaluacionHasEncabezadoPK> {

    @Query("SELECT e FROM EvaluacionHasEncabezado e WHERE e.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion = :idEvaluacion")
    List<EvaluacionHasEncabezado> findByIdEvaluacion(@Param("idEvaluacion") Long idEvaluacion);
}
