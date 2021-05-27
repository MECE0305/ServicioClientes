package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezado;
import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezadoPK;

public interface EvaluacionHasEncabezadoService {

	public List<EvaluacionHasEncabezado> findAll();

	public EvaluacionHasEncabezado findById(EvaluacionHasEncabezadoPK id);

	public EvaluacionHasEncabezado save(EvaluacionHasEncabezado entidad);

	public void delete(EvaluacionHasEncabezadoPK id);
}
