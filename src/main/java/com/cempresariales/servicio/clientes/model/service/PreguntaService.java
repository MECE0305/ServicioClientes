package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

public interface PreguntaService {

	public List<Pregunta> findAll();
	public Pregunta findById(Long id);
	public Pregunta save(Pregunta entidad);
	public void delete(Long id);
	
	
	public List<Pregunta> findPreguntasByCategorias(Collection<Long> expresion);

}
