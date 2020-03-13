package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.PromedioDao;
import com.cempresariales.servicio.commons.model.entity.Promedio;

@Service
public class PromedioServiceImp implements IPromedioService{

	@Autowired
	private PromedioDao promedioDao;
	
	@Override
	public List<Promedio> findAll() {
		return (List<Promedio>)promedioDao.findAll();
	}

	@Override
	public Promedio findById(Long id) {
		return promedioDao.findById(id).orElse(null);
	}

	@Override
	public Promedio save(Promedio promedio) {
		promedioDao.save(promedio);
		return null;
	}

	@Override
	public void deleteById(Long id) {
		promedioDao.deleteById(id);
		
	}

}
