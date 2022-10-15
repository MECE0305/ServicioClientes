package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezado;
import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezadoPK;
import com.cempresariales.servicio.clientes.model.dao.EvaluacionHasEncabezadoDao;

@Service
public class EvaluacionHasEncabezadoServiceImp implements EvaluacionHasEncabezadoService {

	@Autowired
	private EvaluacionHasEncabezadoDao repo;

	@Override
	public List<EvaluacionHasEncabezado> findAll() {
		return (List<EvaluacionHasEncabezado>) repo.findAll();
	}

	@Override
	public EvaluacionHasEncabezado findById(EvaluacionHasEncabezadoPK id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EvaluacionHasEncabezado save(EvaluacionHasEncabezado entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(EvaluacionHasEncabezadoPK id) {
		repo.deleteById(id);
	}

	@Override
	public List<EvaluacionHasEncabezado> findByIdEvaluacion(Long idEvaluacion) {
		return repo.findByIdEvaluacion(idEvaluacion);
	}

}
