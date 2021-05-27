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

import com.cempresariales.servicio.clientes.model.dao.RolDao;
import com.cempresariales.servicio.commons.model.entity.Rol;

@Service
public class RolServiceImp implements IRolService {

	@Autowired
	private RolDao rolDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Rol> findAll() {
		return rolDao.findAll();
	}

	@Override
	public Rol findById(Long id) {
		return rolDao.findById(id).orElse(null);
	}

	@Override
	public Rol save(Rol rol) {
		return rolDao.save(rol);
	}

	@Override
	public void delete(Long id) {
		rolDao.deleteById(id);
	}

	@Override
	public List<Rol> findRolByArea(Long idArea) {
		return rolDao.findRolByArea(idArea);
	}

	@Override
	public List<Rol> findRolByEmpleados(Collection<Long> expresion) {
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
					"select re.rol from RolHasEmpleado re where re.empleado.idEmpleado  in " + "(" + cadena
							+ ") group by re.rol");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
