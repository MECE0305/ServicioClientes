package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.commons.model.entity.EstadoEvaluacion;
import com.cempresariales.servicio.clientes.model.dao.EstadoEvaluacionDao;

@Service
public class EstadoEvaluacionServiceImp implements EstadoEvaluacionService {

	@Autowired
	private EstadoEvaluacionDao repo;

	@Override
	public List<EstadoEvaluacion> findAll() {
		return (List<EstadoEvaluacion>) repo.findAll();
	}

	@Override
	public EstadoEvaluacion findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EstadoEvaluacion save(EstadoEvaluacion entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	

}
