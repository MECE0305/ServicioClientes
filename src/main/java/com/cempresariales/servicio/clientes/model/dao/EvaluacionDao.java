package com.cempresariales.servicio.clientes.model.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.Evaluacion;


public interface EvaluacionDao extends JpaRepository<Evaluacion, Long> {
	
	
/*	  @Query("select eva from Evaluacion eva where eva.idEmpleado in "
			+ "(select e.empleadoPK.idEmpleado from Empleado e where e.agencia.idAgencia in :expresion)")*/
@Query("select eva from Evaluacion eva where eva.idEmpleado in "
		+ "(select e.idEmpleado from Empleado e where e.agenciaIdAgencia.idAgencia in :expresion)")
	public List<Evaluacion> findEvaByAgencias(@Param("expresion") Collection<Long> expresion);
	 

	@Query("select cle.evaluacion from ChecklistHasEvaluacion cle where cle.evaluacion.idEvaluacion = ?1 and "
			+ " cle.checklist.idChecklist in (select clcp.checklist.idChecklist from ChecklistHasCatalogoPregunta clcp where clcp.catalogoPregunta.categoria.idCategoria = ?2)")
	List<Evaluacion> findByEvaluacionCategoria(@Param("idEvaluacion") Long idEvaluacion,
			@Param("idCategoria") Long idCategoria);





}
