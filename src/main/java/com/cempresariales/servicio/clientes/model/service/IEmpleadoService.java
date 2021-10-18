package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.TiempoAtencionDTO;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;

public interface IEmpleadoService {
	public List<Empleado> findAll();

	public Empleado findById(Long id);

	public Empleado save(Empleado categoria);

	public void delete(Long id);

	public List<Empleado> findByAgenciaIdAgencia(Agencia agencia);
	
	public List<Empleado> findEmpleadoByAgencias(Collection<Long> expresion);

	public List<TiempoAtencionDTO> findEmpleadoAreaTAtencion(Long idAgencia);

}
