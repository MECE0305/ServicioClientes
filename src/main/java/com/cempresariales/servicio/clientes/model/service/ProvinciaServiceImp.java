package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.ProvinciaDao;
import com.cempresariales.servicio.commons.model.entity.Provincia;

@Service
public class ProvinciaServiceImp implements IProvinciaService{

	@Autowired
	private ProvinciaDao provinciaDao;
	
	@Override
	public List<Provincia> findAll() {
		return (List<Provincia>) provinciaDao.findAll();
	}

	@Override
	public Provincia findById(Long id) {
		return provinciaDao.findById(id).orElse(null);
	}

	@Override
	public Provincia save(Provincia provincia) {
		return provinciaDao.save(provincia);
	}

	@Override
	public void delete(Long id) {
		provinciaDao.deleteById(id);
		
	}

}
