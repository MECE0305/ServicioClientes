package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.ZonaDao;
import com.cempresariales.servicio.commons.model.entity.Zona;

@Service
public class ZonaServiceImp implements IZonaService{

	@Autowired
	private ZonaDao zonaDao;
	@Override
	public List<Zona> findAll() {
		return (List<Zona>) zonaDao.findAll();
	}

	@Override
	public Zona findById(Long id) {
		return zonaDao.findById(id).orElse(null);
	}

	@Override
	public Zona save(Zona zona) {
		return zonaDao.save(zona);
	}

	@Override
	public void delete(Long id) {
		zonaDao.deleteById(id);
		
	}

}
