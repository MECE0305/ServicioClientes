package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.CatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.CatalogoPreguntaPK;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

public interface CatalogoPreguntaService {

	public List<CatalogoPregunta> findAll();
	
	public List<CatalogoPregunta> findByIdCategoria(Long idCategoria);
	
	public CatalogoPregunta findById(CatalogoPreguntaPK id);
	
	public CatalogoPregunta findByPK(Long idCategoria, Long idPregunta, Long idPeso);
	
	public CatalogoPregunta save(CatalogoPregunta entidad);
	public void delete(CatalogoPreguntaPK id);
	
	public List<Pregunta> findPreguntasByCategoria(Long id);
	
	public CatalogoPregunta findByIdCatalogoPregunta(Long id);
}
