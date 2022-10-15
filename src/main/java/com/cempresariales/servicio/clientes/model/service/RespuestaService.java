package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.Respuesta;
import org.springframework.data.repository.query.Param;


public interface RespuestaService {

	public List<Respuesta> findAll();
	public Respuesta findById(Long id);
	public Respuesta save(Respuesta respuesta);
	public void delete(Long id);
	
	public List<Respuesta> findByCategoria(Long idCategoria);

	
	
	public List<Respuesta> findRespuestaByChecklistEvaluacion(List<ChecklistHasEvaluacion> lista);
	public List<Respuesta> findByIdEvaluacionIdChecklist(Long idEvaluacion, Long idChecklist);

	public List<Respuesta> findByIdEmpresa(@Param("idEmpresa") Long idEmpresa);
	public List<Respuesta> findByIdEmpleado(@Param("idEmpleado") Long idEmpleado);

	public Double puntuacionEmpleadoByEvaluacion(Long idEvaluacion, Long idEmpleado);

	public List<Respuesta> findByIdEvaluacion(Long evaluacionIdEvaluacion);



}
