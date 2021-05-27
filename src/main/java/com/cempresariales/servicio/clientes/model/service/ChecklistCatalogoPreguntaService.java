package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPreguntaPK;



public interface ChecklistCatalogoPreguntaService {

	public List<ChecklistHasCatalogoPregunta> findAll();
	public ChecklistHasCatalogoPregunta findById(ChecklistHasCatalogoPreguntaPK id);
	public ChecklistHasCatalogoPregunta save(ChecklistHasCatalogoPregunta entidad);
	public void delete(ChecklistHasCatalogoPreguntaPK id);
	public List<ChecklistHasCatalogoPregunta> findByChecklistID(Long id);
	public List<ChecklistHasCatalogoPregunta> findByCategoria(Long id);
	public List<ChecklistHasCatalogoPregunta> findByCategoriaChecklist(Long idCategoria, Long idChecklist);
	
	
	
	

}
