package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.clientes.model.dto.AreaDTO;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Area;

public interface AreaService {

	public List<Area> findAll();

	public Area findById(Long id);

	public Area save(Area entidad);

	public void delete(Long id);

	public List<Area> findAreasByRoles(Collection<Long> expresion);

	public List<AreaDTO> findPromedioPorCategoriaArea(Long idEmpresa, Long idAgencia);
	public List<AreaDTO> findPromedioPorCategoriaAreaZonaAgencia(Long idEmpresa, Long idAgencia,Long idEncabezado, Long idZona, Long idArea);

	public List<AreaDTO> findPromedioPorAgenciaArea(Long idEmpresa, Long idAgencia, Long idRol, Long idZonaE, Long idEncabezado, Integer groupBy);

	public AreaDTO findPromedioPorAgencia(Long idAgencia);
	public AreaDTO findPromedioPorAgenciaAreaZonaEnca(Long idAgencia, Long idArea, Long idZonaE, Long idEncabezado);

	public List<AreaDTO> findPromedioAreaPorEmpresa(Long idEmpresa);

	public List<AreaDTO> findPromedioPorCategoriaAreaEmpresa(Long idEmpresa);

	public AreaDTO findPromedioPorCategoriaEmpresa(Long idEmpresa);

	public List<Area> findByEmpresa(Long idEmpresa);

	public List<AreaDTO> findPromedioPorAgenciaZonarol(Long idRol, Long idZona, Long idAgencia);
}
