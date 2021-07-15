package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.ZonaEstructural;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;

public interface ZonaEstructuralDao extends JpaRepository<ZonaEstructural, Long> {
	@Query("select zona from ZonaEstructural zona where zona.idZonaEstructural in "
			+ "(select ze.zonaEstructuralIdZonaEstructural.idZonaEstructural from ZonaEstructuralHasCiudad ze where ze.zonaEstructuralIdCiudad.idCiudad = ?1)")
	public List<ZonaEstructural> findZonaEstructuralByCiudad(@Param("idCiudad") Long id);
	
	@Query("select ze from ZonaEstructuralHasCiudad ze where ze.zonaEstructuralIdZonaEstructural.idZonaEstructural = ?1")
	public List<ZonaEstructuralHasCiudad> findZonaEstructuralCiudadByZonaEstructura(@Param("idZonaEstructura") Long id);

}
