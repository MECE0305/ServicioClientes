package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacionPK;

public interface ChecklistHasEvaluacionDao extends JpaRepository<ChecklistHasEvaluacion, ChecklistHasEvaluacionPK> {

	@Query("select cle from ChecklistHasEvaluacion cle where cle.checklist.idChecklist = ?1 and cle.evaluacion.idEvaluacion = ?2")
	List<ChecklistHasEvaluacion> findByCheckListEvaluacion(@Param("idCheckList") Long idCheckList,
			@Param("idEvaluacion") Long idEvaluacion);
	
	
	

}
