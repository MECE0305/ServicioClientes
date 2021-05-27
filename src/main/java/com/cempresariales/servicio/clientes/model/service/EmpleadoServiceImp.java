package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.EmpleadoDao;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;

@Service
public class EmpleadoServiceImp implements IEmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public void delete(Long id) {
		empleadoDao.deleteById(id);
	}

	@Override
	public List<Empleado> findByAgenciaIdAgencia(Agencia agencia) {
		return empleadoDao.findByAgenciaIdAgencia(agencia);
	}

	@Override
	public List<Empleado> findEmpleadoByAgencias(Collection<Long> expresion) {
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
					"select emp from Empleado emp where emp.agenciaIdAgencia.idAgencia in "
							+ " (select ag.idAgencia from Agencia ag where ag.idAgencia in " + "(" + cadena + ")"
							+ ")");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<Empleado> buscarAllEmpleado() {
		
		String queryStr =
			      "select NEW Empleado(emp.idEmpleado, emp.activoEmpleado, emp.apellidoEmpleado, emp.ciEmpleado,"
			      + "emp.creaEmpleado,emp.fotoEmpleado,emp.generoEmpleado,emp.mailEmpleado,emp.nombreEmpleado,"
			      + "emp.telefonoEmpleado) from Empleado emp";
			  TypedQuery<Empleado> query =
					  entityManager.createQuery(queryStr, Empleado.class);
			  List<Empleado> results = query.getResultList();
		
		/*StringBuilder queryString = new StringBuilder(
				"select emp.idEmpleado, emp.activoEmpleado, emp.apellidoEmpleado, emp.ciEmpleado,"
						+ "emp.creaEmpleado,emp.fotoEmpleado,emp.generoEmpleado,emp.mailEmpleado,emp.nombreEmpleado,"
						+ "emp.telefonoEmpleado from Empleado emp");

		Query query = entityManager.createQuery(queryString.toString());*/

		return results;
	}

}
