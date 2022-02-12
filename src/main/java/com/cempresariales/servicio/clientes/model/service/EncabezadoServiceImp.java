package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.commons.model.entity.Encabezado;
import com.cempresariales.servicio.clientes.model.dao.EncabezadoDao;

@Service
public class EncabezadoServiceImp implements EncabezadoService {

	@Autowired
	private EncabezadoDao repo;

	@Override
	public List<Encabezado> findAll() {
		return (List<Encabezado>) repo.findAll();
	}

	@Override
	public Encabezado findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Encabezado save(Encabezado entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Encabezado> findEncByEmpresa(Long id) {
		return repo.listaEncabezadoPorEmpresa(id);
	}

}
