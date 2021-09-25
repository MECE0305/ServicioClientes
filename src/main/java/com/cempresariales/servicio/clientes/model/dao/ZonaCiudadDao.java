package com.cempresariales.servicio.clientes.model.dao;

import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaCiudadDao extends JpaRepository<ZonaEstructuralHasCiudad, Long> {

}
