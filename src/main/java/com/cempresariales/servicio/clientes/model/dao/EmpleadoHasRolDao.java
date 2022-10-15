package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleadoPK;

public interface EmpleadoHasRolDao extends JpaRepository<RolHasEmpleado, RolHasEmpleadoPK> {

	@Query("select rha from RolHasEmpleado rha where rha.rolHasEmpleadoPK.empleadoIdEmpleado = ?1")
	List<RolHasEmpleado> findByEmp(@Param("idRol") Long id);

	@Query("select rha from RolHasEmpleado rha where rha.empleado.activoEmpleado = true")
	List<RolHasEmpleado> findRolEmpleadoActive();

	@Query("select r.nombreRol from RolHasEmpleado rha JOIN Rol r on r.idRol= rha.rolHasEmpleadoPK.rolIdRol " +
			" where rha.rolHasEmpleadoPK.empleadoIdEmpleado = ?1")
	List<String> nombreRolesPorEmpleado(Long id);

	
	@Query("select rha.empleado from RolHasEmpleado rha where rha.rol.idRol = ?1")
	List<Empleado> findByRol(@Param("idRol") Long id);
	
	
	
}
