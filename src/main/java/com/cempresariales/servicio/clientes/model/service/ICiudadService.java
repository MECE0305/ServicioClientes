package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Ciudad;

public interface ICiudadService {

	public List<Ciudad> findAll();

	public Ciudad findById(Long id);

	public Ciudad save(Ciudad ciudad);

	public void deleteById(Long id);

	public List<Ciudad> findCiudadesByAgencias(Collection<Long> expresion);
}
