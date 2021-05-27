package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezado;
import com.cempresariales.servicio.commons.model.entity.EvaluacionHasEncabezadoPK;
import com.cempresariales.servicio.clientes.model.dao.EvaluacionEncabezadoDao;

@Service
public class EvaluacionEncabezadoServiceImpl implements EvaluacionEncabezadoService {

	@Autowired
	private EvaluacionEncabezadoDao evaluacionDao;

	@Override
	@Transactional(readOnly = true)
	public List<EvaluacionHasEncabezado> findAll() {
		return (List<EvaluacionHasEncabezado>) evaluacionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EvaluacionHasEncabezado findById(EvaluacionHasEncabezadoPK id) {
		return evaluacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EvaluacionHasEncabezado save(EvaluacionHasEncabezado evaluacion) {
		return evaluacionDao.save(evaluacion);
	}

	@Override
	@Transactional
	public void delete(EvaluacionHasEncabezadoPK id) {
		evaluacionDao.deleteById(id);
	}

	@Override
	public List<EvaluacionHasEncabezado> findByEncabezado(Long id) {
		return evaluacionDao.findByEncabezado(id);
	}

	@Override
	public List<EvaluacionHasEncabezado> findByEvaluacion(Long id) {
		return evaluacionDao.findByEvaluacion(id);
	}

}
