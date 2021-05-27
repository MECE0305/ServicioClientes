package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.Respuesta;

public interface RespuestaDao extends JpaRepository<Respuesta, Long>{
	

	@Query("select r from Respuesta r where r.idCatalogoPregunta in "
			+ "(select cp.idCatalogoPregunta from CatalogoPregunta cp where cp.categoria.idCategoria = ?1)")
	public List<Respuesta> findByCategoria(@Param("idCategoria") Long idCategoria);

	@Query("select r from Respuesta r where r.checklistHasEvaluacion.checklistHasEvaluacionPK.evaluacionIdEvaluacion = ?1 and r.checklistHasEvaluacion.checklistHasEvaluacionPK.checklistIdChecklist = ?2")
	public List<Respuesta> findByIdEvaluacionIdChecklist(@Param("evaluacionIdEvaluacion") Long evaluacionIdEvaluacion,@Param("checklistIdChecklist") Long checklistIdChecklist);

	@Query("select r from Respuesta r where r.checklistHasEvaluacion.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion from ChecklistHasEvaluacion ce where ce.activo = true and ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ev.idEvaluacion from Evaluacion ev where ev.activoEvaluacion = true and  ev.idEmpleado in (" +
			"select em.idEmpleado from Empleado  em where em.activoEmpleado= true and em.agenciaIdAgencia.idAgencia in (" +
			"select ag.idAgencia from Agencia ag where ag.empresaIdEmpresa.idEmpresa = ?1 and ag.activoAgencia = true))))" +
			"and" +
			" r.checklistHasEvaluacion.checklistHasEvaluacionPK.checklistIdChecklist in (" +
			"select ce.checklistHasEvaluacionPK.checklistIdChecklist from ChecklistHasEvaluacion ce where ce.activo = true and ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ev.idEvaluacion from Evaluacion ev where ev.activoEvaluacion = true and  ev.idEmpleado in (" +
			"select em.idEmpleado from Empleado  em where em.activoEmpleado= true and em.agenciaIdAgencia.idAgencia in ("  +
			"select ag.idAgencia from Agencia ag where ag.empresaIdEmpresa.idEmpresa = ?1 and ag.activoAgencia = true))))")
	public List<Respuesta> findByIdEmpresa(@Param("idEmpresa") Long idEmpresa);


	@Query("select r from Respuesta r where r.checklistHasEvaluacion.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion from ChecklistHasEvaluacion ce where ce.activo = true and ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ev.idEvaluacion from Evaluacion ev where ev.activoEvaluacion = true and  ev.idEmpleado =?1))" +
			"and "+
			" r.checklistHasEvaluacion.checklistHasEvaluacionPK.checklistIdChecklist in (" +
			"select ce.checklistHasEvaluacionPK.checklistIdChecklist from ChecklistHasEvaluacion ce where ce.activo = true and ce.checklistHasEvaluacionPK.evaluacionIdEvaluacion in (" +
			"select ev.idEvaluacion from Evaluacion ev where ev.activoEvaluacion = true and  ev.idEmpleado =?1))")
	public List<Respuesta> listarByIdEmpleado(@Param("idEmpleado") Long idEmpleado);



}
