package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.clientes.model.dto.AreaDTO;
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

	@Override
	public List<AreaDTO> findPromedioPorCategoriaArea(Long idEmpresa, Long idAgencia) {

		try {

			String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, pond.idCategoria, pond.nombreCategoria, avg (pond.suma), pond.peso)" +
					" from RolHasEmpleado re JOIN " +
					" Rol r ON re.rol.idRol = r.idRol JOIN" +
					" Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN"+
					" Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
					" Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
					" Ponderadoporcategoria pond ON eva.idEvaluacion = pond.idEvaluacion JOIN" +
					" Area ar ON ar.idArea = r.areaIdArea.idArea "+
					" WHERE ag.empresaIdEmpresa.idEmpresa = ?1  and ag.idAgencia = ?2"+
					" group by r.nombreRol, pond.nombreCategoria"+
					" ORDER BY r.nombreRol, pond.nombreCategoria ";


			System.out.println("Sentencia : " +queryStr);
			//   Query query = entityManager.createQuery(queryStr.toString());

			TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
			query.setParameter(1, idEmpresa);
			query.setParameter(2, idAgencia);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	@Override
	public List<AreaDTO> findPromedioPorAgenciaArea(Long idEmpresa, Long idAgencia) {

		try {

			String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, ar.idArea, ar.nombreArea, avg (eva.puntajeEvaluacion), avg (eva.puntajeEvaluacion))" +
					" from Area ar JOIN" +
					" Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
					" RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
					"Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
					" Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
					" Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
					" WHERE ag.empresaIdEmpresa.idEmpresa = ?1  and ag.idAgencia = ?2"+
					" group by ar.nombreArea"+
					" ORDER BY ar.nombreArea ";


			TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
			query.setParameter(1, idEmpresa);
			query.setParameter(2, idAgencia);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}


}
