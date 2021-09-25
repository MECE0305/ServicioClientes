package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.CategoriaTopDTO;
import com.cempresariales.servicio.clientes.model.dto.RolDTO;
import com.cempresariales.servicio.clientes.model.dto.RolTopDTO;
import com.cempresariales.servicio.commons.model.entity.Rol;


public interface IRolService {

	public List<Rol> findAll();
	public Rol findById(Long id);
	public Rol save(Rol categoria);
	public void delete(Long id);
	
	
	public List<Rol> findRolByArea(Long idArea);
	
	
	public List<Rol> findRolByEmpleados(Collection<Long> expresion);

	public List<Rol> rolesByAgencias(Collection<Long> IdAgencias);

	public List<RolTopDTO> findTop10ByEmpresa(Long idEmpresa, String orderBy, int limit);

	public List<Rol> findByEmpresa(Long idEmpresa);

}
