package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.SectorDao;
import com.cempresariales.servicio.commons.model.entity.Sector;

@Service
public class SectorServiceImp implements SectorService {

	@Autowired
	private SectorDao repo;

	@Override
	public List<Sector> findAll() {
		return (List<Sector>) repo.findAll();
	}

	@Override
	public Sector findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Sector save(Sector entidad) {
		return repo.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
