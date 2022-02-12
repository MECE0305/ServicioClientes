package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.ZonaEstructural;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;

public interface ZonaEstructuralDao extends JpaRepository<ZonaEstructural, Long> {
	@Query("select zona from ZonaEstructural zona where zona.idZonaEstructural in "
			+ "(select ze.zonaEstructuralIdZonaEstructural.idZonaEstructural from ZonaEstructuralHasCiudad ze where ze.zonaEstructuralIdCiudad.idCiudad = ?1)")
	public List<ZonaEstructural> findZonaEstructuralByCiudad(@Param("idCiudad") Long id);
	
	@Query("select ze from ZonaEstructuralHasCiudad ze where ze.zonaEstructuralIdZonaEstructural.idZonaEstructural = ?1")
	public List<ZonaEstructuralHasCiudad> findZonaEstructuralCiudadByZonaEstructura(@Param("idZonaEstructura") Long id);

	@Query("select ze from ZonaEstructuralHasCiudad ze where ze.zonaEstructuralIdCiudad.idCiudad = ?1 and ze.zonaEstructuralIdZonaEstructural.idZonaEstructural = ?2")
	public ZonaEstructuralHasCiudad findZonaEstructuralCiudadByCiudadZonaEstructura(@Param("idCiudad") Long idCiudad,@Param("idZonaEstructura") Long idZona);


	@Query("select ze from ZonaEstructural ze INNER JOIN " +
			" Agencia ag ON ag.idZonaEstructural = ze.idZonaEstructural INNER JOIN " +
			" Empleado emp ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia INNER JOIN " +
			" RolHasEmpleado rhe ON rhe.rolHasEmpleadoPK.empleadoIdEmpleado = emp.idEmpleado " +
			" where rhe.rol.idRol = ?1 group by ze.idZonaEstructural")
	public List<ZonaEstructural> findZonaEstructuralByRol(@Param("idRol") Long idRol);


	@Query("select ze from ZonaEstructural ze INNER JOIN " +
			" Agencia ag ON ag.idZonaEstructural = ze.idZonaEstructural " +
			" where ag.empresaIdEmpresa.idEmpresa = ?1 group by ze.idZonaEstructural")
	public List<ZonaEstructural> findZonaEstructuralByEmpresa(@Param("idEmpresa") Long idEmpresa);



}
