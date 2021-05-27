package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Peso;

public interface PesoService {

	public List<Peso> findAll();
	public Peso findById(Long id);
	public Peso save(Peso entidad);
	public void delete(Long id);
}
