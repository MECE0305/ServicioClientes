package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO;
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


	/*select sum(valor_calculado_respuesta) from respuesta where cumple_respuesta = true and  checklist_has_evaluacion_evaluacion_id_evaluacion in (
			select evaluacion_id_evaluacion from checklist_has_evaluacion where activo = true and evaluacion_id_evaluacion in (
			select id_evaluacion from evaluacion where activo_evaluacion = true and  id_evaluacion = 84 and id_empleado = 223));*/
	@Override
	public Double puntuacionEmpleadoByEvaluacion(Long idEvaluacion, Long idEmpleado) {
		try {

			String queryStr = "select sum(re.valorCalculadoRespuesta) from Respuesta re where re.cumpleRespuesta = true " +
					"and re.checklistHasEvaluacion.evaluacion.idEvaluacion in (" +
					"select ce.evaluacion.idEvaluacion from ChecklistHasEvaluacion ce where ce.activo = true and ce.evaluacion.idEvaluacion in (" +
					"select ev.idEvaluacion from Evaluacion ev where ev.activoEvaluacion = true and  ev.idEvaluacion = ?1 and ev.idEmpleado = ?2))";
			Query consulta =
					entityManager.createQuery(queryStr);
			consulta.setParameter(1, idEvaluacion);
			consulta.setParameter(2, idEmpleado);

			return (Double) consulta.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0.00;
		}
	}



}
