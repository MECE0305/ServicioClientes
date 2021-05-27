package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.CatalogoPreguntaDao;
import com.cempresariales.servicio.commons.model.entity.CatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.CatalogoPreguntaPK;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

@Service
public class CatalogoPreguntaServiceImp implements CatalogoPreguntaService {

	@Autowired
	private CatalogoPreguntaDao repo;

	@Override
	public List<CatalogoPregunta> findAll() {
		return (List<CatalogoPregunta>) repo.findAll();
	}

	@Override
	public CatalogoPregunta findById(CatalogoPreguntaPK id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public CatalogoPregunta save(CatalogoPregunta entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(CatalogoPreguntaPK id) {
		repo.deleteById(id);
	}

	@Override
	public List<Pregunta> findPreguntasByCategoria(Long id) {
		return repo.findPreguntasByCategoria(id);
	}

	@Override
	public CatalogoPregunta findByPK(Long idCategoria, Long idPregunta, Long idPeso) {
		return repo.findByPK(idCategoria, idPregunta, idPeso);
	}

	@Override
	public CatalogoPregunta findByIdCatalogoPregunta(Long id) {
		return repo.findByIdCatalogoPregunta(id);
	}

	@Override
	public List<CatalogoPregunta> findByIdCategoria(Long idCategoria) {
		return repo.findByIdCategoria(idCategoria);
	}

}
