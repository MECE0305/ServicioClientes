package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Area;

public interface AreaService {

	public List<Area> findAll();

	public Area findById(Long id);

	public Area save(Area entidad);

	public void delete(Long id);

	public List<Area> findAreasByRoles(Collection<Long> expresion);

}
