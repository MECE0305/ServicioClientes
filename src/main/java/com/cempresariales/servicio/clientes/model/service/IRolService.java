package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Rol;


public interface IRolService {

	public List<Rol> findAll();
	public Rol findById(Long id);
	public Rol save(Rol categoria);
	public void delete(Long id);
	
	
	public List<Rol> findRolByArea(Long idArea);
	
	
	public List<Rol> findRolByEmpleados(Collection<Long> expresion);

	public List<Rol> rolesByAgencias(Collection<Long> IdAgencias);

	
}
