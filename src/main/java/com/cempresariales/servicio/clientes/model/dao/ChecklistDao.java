package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.Categoria;
import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.Pregunta;
import com.cempresariales.servicio.commons.model.entity.Rol;

public interface ChecklistDao extends JpaRepository<Checklist, Long> {

	@Query("select p from Pregunta p where p.idPregunta in "
			+ "(select pp.catalogoPregunta.pregunta.idPregunta from ChecklistHasCatalogoPregunta pp where pp.checklist.idChecklist = ?1)")
	public List<Pregunta> countPreguntasByChecklist(@Param("idRol") Long id);

	@Query("select pp.catalogoPregunta.categoria from ChecklistHasCatalogoPregunta pp where pp.checklist.idChecklist = ?1 "
			+ "group by pp.catalogoPregunta.categoria.idCategoria,pp.ordenBloque order by pp.ordenBloque asc")
	public List<Categoria> countCategoriaByChecklist(@Param("idChecklist") Long id);

	public List<Checklist> findByRolIdRol(Rol rolIdRol);

}
