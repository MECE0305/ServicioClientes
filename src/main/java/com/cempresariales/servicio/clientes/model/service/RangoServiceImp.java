package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.RangoDao;
import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

@Service
public class RangoServiceImp implements RangoService {

	@Autowired
	private RangoDao repo;

	@Override
	public List<RangoDesempenio> findAll() {
		return (List<RangoDesempenio>) repo.findAll();
	}

	@Override
	public RangoDesempenio findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public RangoDesempenio save(RangoDesempenio entidad) {
		return repo.save(entidad);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public RangoDesempenio findByRangoAndEmpresa(Double rango, Long idEmpresa) {
		return repo.findByRangoAndEmpresa(rango, idEmpresa);
	}

}
