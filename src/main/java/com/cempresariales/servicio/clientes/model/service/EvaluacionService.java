package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO;
import com.cempresariales.servicio.clientes.model.dto.EncabezadoReporteBloquesDTO;
import com.cempresariales.servicio.clientes.model.dto.ReporteBloquesDTO;
import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import com.cempresariales.servicio.clientes.model.dto.BuscadorDTO;


public interface EvaluacionService {

	public List<Evaluacion> findAll();
	public Evaluacion findById(Long id);
	public Evaluacion save(Evaluacion evaluacion);
	public void delete(Long id);

	public List<Evaluacion> findByParams(BuscadorDTO buscador);
	public List<Evaluacion> findBySegmentacion(Long idRegion, Long idZona, Long idProvincia, Long idCiudad, Long idZonaEstructural);

	public List<Evaluacion> findByFiltroTabClienteAndRol(Long idCliente, Long idEmpresa,Long idSector,Long idAgencia, Long idEmpleado, Long idRol, Long idArea);


	public List<Evaluacion> findEvaByAgencias(Collection<Long> expresion);
	public List<Evaluacion> findByEvaluacionCategoria(Long idEvaluacion, Long idCategoria);


	public String reporteBloquesbyAgencias(Collection<Long> IdAgencias);
	public List<DetalleReporteBloquesDTO> detalleBloquesbyAgencias(Long idChecklist, Long idEvaluacion);

	public List<Evaluacion> findEvaByAgenciasDTO(Long idUsuario,Long idEmpresa,String agencias);

	}
