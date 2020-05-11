package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Sector;


public interface SectorService {

	public List<Sector> findAll();
	public Sector findById(Long id);
	public Sector save(Sector entidad);
	public void deleteById(Long id);
}
