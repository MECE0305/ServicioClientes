package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.ChecklistCatalogoPreguntaDao;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPreguntaPK;

@Service
public class ChecklistCatalogoPreguntaServiceImp implements ChecklistCatalogoPreguntaService {

	@Autowired
	private ChecklistCatalogoPreguntaDao repo;

	@Override
	public List<ChecklistHasCatalogoPregunta> findAll() {
		return (List<ChecklistHasCatalogoPregunta>) repo.findAll();
	}

	@Override
	public ChecklistHasCatalogoPregunta findById(ChecklistHasCatalogoPreguntaPK id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public ChecklistHasCatalogoPregunta save(ChecklistHasCatalogoPregunta entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(ChecklistHasCatalogoPreguntaPK id) {
		repo.deleteById(id);
	}

	@Override
	public List<ChecklistHasCatalogoPregunta> findByChecklistID(Long id) {
		return (List<ChecklistHasCatalogoPregunta>) repo.findByChecklistID(id);
	}

	@Override
	public List<ChecklistHasCatalogoPregunta> findByCategoria(Long id) {
		return (List<ChecklistHasCatalogoPregunta>) repo.findByCategoria(id);
	}

	@Override
	public List<ChecklistHasCatalogoPregunta> findByCategoriaChecklist(Long idCategoria, Long idChecklist) {
		return repo.findByCategoriaChecklist(idCategoria, idChecklist);
	}

	
	
	
	

}
