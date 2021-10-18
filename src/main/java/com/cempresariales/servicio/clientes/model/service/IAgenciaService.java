package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;

public interface IAgenciaService {
	public List<Agencia> findAll();
	public Agencia findById(Long id);
	public Agencia save(Agencia categoria);
	public void delete(Long id);
	
	public List<Agencia> findByEmpresaIdEmpresa(Empresa empresa);

	public List<AgenciasTopDTO> findTop10ByEmpresa(Long idEmpresa, String orderBy, int limit);

	public List<AgenciasDTO> findPromedioAgenciasPorEmpresa(List<Long> IdAgencias, Long idEmpresa);

	public List<AgenciasDTO> findAgenciasPorRol(Long idEmpresa, Long idRol, List<Long> IdAgencias);

	public List<AgenciasDTO> findPromedioPorAgencia(Long idEmpresa);

	public AgenciasDTO findPromedioPorEmpresa(Long idEmpresa);

	public AgenciasDTO findPromedioEmpresaRol(Long idEmpresa);
}
