package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacionPK;
import com.cempresariales.servicio.commons.model.entity.Evaluacion;

public interface ChecklistHasEvaluacionService {

	public List<ChecklistHasEvaluacion> findAll();

	
	public List<ChecklistHasEvaluacion> findByCheckListEvaluacion(Long idChecklist, Long idEvaluacion);
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByCheckList(Long idChecklist);
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluacion(Long idEvaluacion);
	public List<Evaluacion> findEvaluacionByCheckList(Long idChecklist);
	public List<Checklist> findCheckListByEvaluacion(Long idEvaluacion);
	
	
	public List<Evaluacion> findEvaluacionByIdsRol(Collection<Long> expresion);

	public List<Evaluacion> findEvaluacionByIdsChecklist(Collection<Long> expresion);

	
	public ChecklistHasEvaluacion findById(ChecklistHasEvaluacionPK id);

	public ChecklistHasEvaluacion save(ChecklistHasEvaluacion entidad);

	public void delete(ChecklistHasEvaluacionPK id);
	
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluaciones(Collection<Long> expresion);

}
