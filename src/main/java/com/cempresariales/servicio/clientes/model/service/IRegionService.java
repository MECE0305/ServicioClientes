package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Region;


public interface IRegionService {

	public List<Region> findAll();
	public Region findById(Long id);
	public Region save(Region region);
	public void delete(Long id);
}
