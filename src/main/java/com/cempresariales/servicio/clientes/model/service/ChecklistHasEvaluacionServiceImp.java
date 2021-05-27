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

import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacionPK;
import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import com.cempresariales.servicio.clientes.model.dao.ChecklistHasEvaluacionDao;

@Service
public class ChecklistHasEvaluacionServiceImp implements ChecklistHasEvaluacionService {

	@Autowired
	private ChecklistHasEvaluacionDao repo;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ChecklistHasEvaluacion> findAll() {
		return (List<ChecklistHasEvaluacion>) repo.findAll();
	}

	@Override
	public ChecklistHasEvaluacion findById(ChecklistHasEvaluacionPK id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public ChecklistHasEvaluacion save(ChecklistHasEvaluacion entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(ChecklistHasEvaluacionPK id) {
		repo.deleteById(id);
	}

	@Override
	public List<ChecklistHasEvaluacion> findByCheckListEvaluacion(Long idChecklist, Long idEvaluacion) {
		return repo.findByCheckListEvaluacion(idChecklist, idEvaluacion);
	}

	@Override
	public List<Evaluacion> findEvaluacionByCheckList(Long idChecklist) {
		try {
			StringBuilder queryString = new StringBuilder(
					"select cle.evaluacion from ChecklistHasEvaluacion cle where cle.checklist.idChecklist = ?1");

			Query query = entityManager.createQuery(queryString.toString());

			query.setParameter(1, idChecklist);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<Checklist> findCheckListByEvaluacion(Long idEvaluacion) {
		try {
			StringBuilder queryString = new StringBuilder(
					"select cle.checklist from ChecklistHasEvaluacion cle where cle.evaluacion.idEvaluacion = ?1");

			Query query = entityManager.createQuery(queryString.toString());
			
			query.setParameter(1, idEvaluacion);


			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByCheckList(Long idChecklist) {
		try {
			StringBuilder queryString = new StringBuilder(
					"select cle from ChecklistHasEvaluacion cle where cle.checklist.idChecklist = ?1");

			Query query = entityManager.createQuery(queryString.toString());

			query.setParameter(1, idChecklist);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluacion(Long idEvaluacion) {
		try {
			StringBuilder queryString = new StringBuilder(
					"select cle from ChecklistHasEvaluacion cle where cle.evaluacion.idEvaluacion = ?1");

			Query query = entityManager.createQuery(queryString.toString());
			
			query.setParameter(1, idEvaluacion);


			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluaciones(Collection<Long> expresion) {
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
					"select cle from ChecklistHasEvaluacion cle where cle.evaluacion.idEvaluacion in (" + cadena + ")");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<Evaluacion> findEvaluacionByIdsRol(Collection<Long> expresion) {
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
					"select cle.evaluacion from ChecklistHasEvaluacion cle where cle.checklist.idChecklist in "
					+ "(select cl.idChecklist from Checklist cl where cl.rolIdRol.idRol in (" + cadena + "))");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<Evaluacion> findEvaluacionByIdsChecklist(Collection<Long> expresion) {
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
					"select cle.evaluacion from ChecklistHasEvaluacion cle where cle.checklist.idChecklist in (" + cadena + ")");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
