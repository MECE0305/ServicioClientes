package com.cempresariales.servicio.clientes.model.service;

import com.cempresariales.servicio.commons.model.entity.Sector;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;

import java.util.List;


public interface ZonaCiudadService {

	public List<ZonaEstructuralHasCiudad> findAll();
	public ZonaEstructuralHasCiudad findById(Long id);
	public ZonaEstructuralHasCiudad save(ZonaEstructuralHasCiudad entidad);
	public void deleteById(Long id);
}
