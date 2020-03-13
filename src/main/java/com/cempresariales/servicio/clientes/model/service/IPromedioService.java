package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Promedio;

public interface IPromedioService {

	public List<Promedio> findAll();
	public Promedio findById(Long id);
	public Promedio save(Promedio promedio);
	public void deleteById(Long id);
}
