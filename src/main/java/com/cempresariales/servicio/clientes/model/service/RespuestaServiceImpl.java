package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.Respuesta;
import com.cempresariales.servicio.clientes.model.dao.RespuestaDao;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private RespuestaDao respuestaDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Respuesta> findAll() {

		return (List<Respuesta>) respuestaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Respuesta findById(Long id) {
		return respuestaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Respuesta save(Respuesta respuesta) {
		return respuestaDao.save(respuesta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		respuestaDao.deleteById(id);

	}

	@Override
	public List<Respuesta> findByCategoria(Long idCategoria) {
		// TODO Auto-generated method stub
		return respuestaDao.findByCategoria(idCategoria);
	}

	@Override
	public List<Respuesta> findRespuestaByChecklistEvaluacion(List<ChecklistHasEvaluacion> lista) {
		try {

			String cadenaChecklist = "";
			String cadenaEvaluacion = "";

			int x = 0;
			for (ChecklistHasEvaluacion cl : lista) {
				cadenaChecklist += cl.getChecklistHasEvaluacionPK().getChecklistIdChecklist() + ",";
				if (x == lista.size() - 1) {
					cadenaChecklist = cadenaChecklist.substring(0, cadenaChecklist.length() - 1);
				}

				cadenaEvaluacion += cl.getChecklistHasEvaluacionPK().getEvaluacionIdEvaluacion() + ",";
				if (x == lista.size() - 1) {
					cadenaEvaluacion = cadenaEvaluacion.substring(0, cadenaEvaluacion.length() - 1);
				}

				x++;
			}

			System.out.println("IN 1: " + cadenaChecklist);
			System.out.println("IN 2: " + cadenaEvaluacion);

			StringBuilder queryString = new StringBuilder(
					"select r from Respuesta r where r.checklistHasEvaluacion.checklist.idChecklist in  ("
							+ cadenaChecklist + ") and r.checklistHasEvaluacion.evaluacion.idEvaluacion in  ("
							+ cadenaEvaluacion + ")");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<Respuesta> findByIdEvaluacionIdChecklist(Long idEvaluacion, Long idChecklist) {
		return respuestaDao.findByIdEvaluacionIdChecklist(idEvaluacion, idChecklist);
	}

	@Override
	public List<Respuesta> findByIdEmpresa(Long idEmpresa) {
		return respuestaDao.findByIdEmpresa(idEmpresa);
	}

	@Override
	public List<Respuesta> findByIdEmpleado(Long idEmpleado) {
		return respuestaDao.listarByIdEmpleado(idEmpleado);
	}


}
