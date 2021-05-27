package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Categoria;
import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.Pregunta;
import com.cempresariales.servicio.commons.model.entity.Rol;

public interface ChecklistService {

	public List<Checklist> findAll();
	public Checklist findById(Long id);
	public Checklist save(Checklist entidad);
	public void delete(Long id);
	
	public List<Checklist> findByRolIdRol(Rol id);
	
	
	public List<Pregunta> countPreguntasByChecklist(Long id);
	
	public List<Categoria> countCategoriaByChecklist(Long id);
	
	
	public List<Checklist> findCheckListByRoles(Collection<Long> expresion);

}
