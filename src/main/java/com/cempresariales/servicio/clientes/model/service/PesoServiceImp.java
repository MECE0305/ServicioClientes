package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.PesoDao;
import com.cempresariales.servicio.commons.model.entity.Peso;

@Service
public class PesoServiceImp implements PesoService {

	@Autowired
	private PesoDao repo;

	@Override
	public List<Peso> findAll() {
		return (List<Peso>) repo.findAll();
	}

	@Override
	public Peso findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Peso save(Peso entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
