package com.cempresariales.servicio.clientes.model.service;

import com.cempresariales.servicio.clientes.model.dao.SectorDao;
import com.cempresariales.servicio.clientes.model.dao.ZonaCiudadDao;
import com.cempresariales.servicio.commons.model.entity.Sector;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaCiudadServiceImp implements ZonaCiudadService {

	@Autowired
	private ZonaCiudadDao repo;

	@Override
	public List<ZonaEstructuralHasCiudad> findAll() {
		return (List<ZonaEstructuralHasCiudad>) repo.findAll();
	}

	@Override
	public ZonaEstructuralHasCiudad findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public ZonaEstructuralHasCiudad save(ZonaEstructuralHasCiudad entidad) {
		return repo.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
