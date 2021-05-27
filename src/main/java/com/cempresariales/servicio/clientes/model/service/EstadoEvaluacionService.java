package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.EstadoEvaluacion;

public interface EstadoEvaluacionService {

	public List<EstadoEvaluacion> findAll();
	public EstadoEvaluacion findById(Long id);
	public EstadoEvaluacion save(EstadoEvaluacion entidad);
	public void delete(Long id);
	
	
	
}
