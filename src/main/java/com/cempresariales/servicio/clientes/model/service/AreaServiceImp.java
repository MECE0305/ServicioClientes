package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.AreaDao;
import com.cempresariales.servicio.commons.model.entity.Area;

@Service
public class AreaServiceImp implements AreaService {

	@Autowired
	private AreaDao rolDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Area> findAll() {
		return rolDao.findAll();
	}

	@Override
	public Area findById(Long id) {
		return rolDao.findById(id).orElse(null);
	}

	@Override
	public Area save(Area rol) {
		return rolDao.save(rol);
	}

	@Override
	public void delete(Long id) {
		rolDao.deleteById(id);
	}

	@Override
	public List<Area> findAreasByRoles(Collection<Long> expresion) {
		try {

			Iterator<Long> iterator = expresion.iterator();
			String cadena = "";
			int x = 0;
			while (iterator.hasNext()) {

				cadena += iterator.next() + ",";
				if (x == expresion.size() - 1) {
					cadena = cadena.substring(0, cadena.length() - 1);
				}

				x++;
			}

			StringBuilder queryString = new StringBuilder(
					"select a from Area a where a.idArea  in (select p.areaIdArea.idArea from Rol p where p.idRol in (" + cadena + "))");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
