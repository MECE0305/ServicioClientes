package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.ZonaEstructural;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;

public interface ZonaEstructuralService {

	public List<ZonaEstructural> findAll();
	public ZonaEstructural findById(Long id);
	public ZonaEstructural save(ZonaEstructural entidad);
	public void deleteById(Long id);
	
	public List<ZonaEstructural> findZonaEstructuralByCiudad(Long id);
	public List<ZonaEstructuralHasCiudad> findZonaEstructuralCiudadByZonaEstructura(Long id);

	public ZonaEstructuralHasCiudad findZonaEstructuralCiudadByCiudadZonaEstructura(Long idCiudad, Long idZona);
	
}
