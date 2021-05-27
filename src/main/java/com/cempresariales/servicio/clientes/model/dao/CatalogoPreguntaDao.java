package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.CatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.CatalogoPreguntaPK;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

public interface CatalogoPreguntaDao extends JpaRepository<CatalogoPregunta, CatalogoPreguntaPK> {

	@Query("select p from Pregunta p where p.idPregunta in "
			+ "(select pp.pregunta.idPregunta from CatalogoPregunta pp where pp.categoria.idCategoria = ?1 order by pp.ordenPregunta asc)")
	public List<Pregunta> findPreguntasByCategoria(@Param("idRol") Long id);

	@Query("select pp from CatalogoPregunta pp where pp.categoria.idCategoria = ?1 order by pp.ordenPregunta asc")
	public List<CatalogoPregunta> findByIdCategoria(@Param("idCategoria") Long id);

	@Query("select pp from CatalogoPregunta pp where pp.idCatalogoPregunta = ?1 order by pp.ordenPregunta asc")
	public CatalogoPregunta findByIdCatalogoPregunta(@Param("idCatalogoPregunta") Long id);

	@Query("select pp from CatalogoPregunta pp where pp.categoria.idCategoria = ?1 and pp.pregunta.idPregunta = ?2 and pp.peso.idPeso = ?3 order by pp.ordenPregunta asc")
	public CatalogoPregunta findByPK(@Param("idCategoria") Long idCategoria, @Param("idPregunta") Long idPregunta,
			@Param("idPeso") Long idPeso);

}
