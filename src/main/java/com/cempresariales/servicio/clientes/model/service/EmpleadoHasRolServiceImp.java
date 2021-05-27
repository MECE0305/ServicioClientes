package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.EmpleadoHasRolDao;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleadoPK;

@Service
public class EmpleadoHasRolServiceImp implements EmpleadoHasRolService {

	@Autowired
	private EmpleadoHasRolDao repo;

	@Override
	public List<RolHasEmpleado> findAll() {
		return repo.findAll();
	}

	@Override
	public RolHasEmpleado findById(RolHasEmpleadoPK id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public RolHasEmpleado save(RolHasEmpleado entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(RolHasEmpleadoPK id) {
		repo.deleteById(id);
	}

	@Override
	public List<RolHasEmpleado> findByEmpleado(Long id) {
		return repo.findByEmpleado(id);
	}

	@Override
	public List<Empleado> findByRol(Long id) {
		return repo.findByRol(id);
	}

}
