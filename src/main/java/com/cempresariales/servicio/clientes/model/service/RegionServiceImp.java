package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.RegionDao;
import com.cempresariales.servicio.commons.model.entity.Region;

@Service
public class RegionServiceImp implements IRegionService{

	@Autowired
	private RegionDao regionDao;
	
	
	@Override
	public List<Region> findAll() {
		return (List<Region>) regionDao.findAll();
	}

	@Override
	public Region findById(Long id) {
		return regionDao.findById(id).orElse(null);
	}

	@Override
	public Region save(Region region) {
		return regionDao.save(region);
	}

	@Override
	public void delete(Long id) {
		regionDao.deleteById(id);
		
	}

}
