package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Provincia;

public interface IProvinciaService {

	public List<Provincia> findAll();
	public Provincia findById(Long id);
	public Provincia save(Provincia provincia);
	public void delete(Long id);
}
