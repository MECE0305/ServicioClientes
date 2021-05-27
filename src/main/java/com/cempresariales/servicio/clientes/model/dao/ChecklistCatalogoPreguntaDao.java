package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPreguntaPK;

public interface ChecklistCatalogoPreguntaDao
		extends JpaRepository<ChecklistHasCatalogoPregunta, ChecklistHasCatalogoPreguntaPK> {

	@Query("select ccp from ChecklistHasCatalogoPregunta ccp where ccp.checklist.idChecklist = ?1 order by ccp.ordenBloque asc")
	public List<ChecklistHasCatalogoPregunta> findByChecklistID(@Param("idCheckList") Long id);

	@Query("select ccp from ChecklistHasCatalogoPregunta ccp where ccp.catalogoPregunta.categoria.idCategoria = ?1 order by ccp.catalogoPregunta.ordenPregunta asc")
	public List<ChecklistHasCatalogoPregunta> findByCategoria(@Param("idCategoria") Long id);
	
	
	@Query("select ccp from ChecklistHasCatalogoPregunta ccp where ccp.catalogoPregunta.categoria.idCategoria = ?1 and ccp.checklist.idChecklist = ?2 order by ccp.catalogoPregunta.ordenPregunta asc")
	public List<ChecklistHasCatalogoPregunta> findByCategoriaChecklist(@Param("idCategoria") Long idCategoria, @Param("idChecklist") Long idChecklist);

}
