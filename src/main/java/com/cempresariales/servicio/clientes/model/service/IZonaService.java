package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Zona;


public interface IZonaService {

	public List<Zona> findAll();
	public Zona findById(Long id);
	public Zona save(Zona zona);
	public void delete(Long id);
}
