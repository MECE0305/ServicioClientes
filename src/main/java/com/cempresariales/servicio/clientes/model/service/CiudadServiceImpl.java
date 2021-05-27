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
import org.springframework.transaction.annotation.Transactional;

import com.cempresariales.servicio.clientes.model.dao.CiudadDao;
import com.cempresariales.servicio.commons.model.entity.Ciudad;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	private CiudadDao ciudadDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> findAll() {
		return (List<Ciudad>) ciudadDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Ciudad findById(Long id) {
		return ciudadDao.findById(id).orElse(null);
	}

	@Override
	public Ciudad save(Ciudad ciudad) {
		return ciudadDao.save(ciudad);
	}

	@Override
	public void deleteById(Long id) {
		ciudadDao.deleteById(id);
	}

	@Override
	public List<Ciudad> findCiudadesByAgencias(Collection<Long> expresion) {
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
					"select c from Ciudad c where c.idCiudad  in (select a.ciudadIdCiudad.idCiudad from Agencia a where a.idAgencia in (" + cadena + "))");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
