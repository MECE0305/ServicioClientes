package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.MedicionDTO;
import com.cempresariales.servicio.clientes.model.dto.RangoDTO;
import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

public interface RangoService {

	public List<RangoDesempenio> findAll();

	public RangoDesempenio findById(Long id);

	public RangoDesempenio save(RangoDesempenio entidad);

	public void deleteById(Long id);
	
	public RangoDesempenio findByRangoAndEmpresa(Double rango, Long idEmpresa);
	
	public List<RangoDesempenio> findByEmpresa(Long idEmpresa);

	public List<RangoDTO> findRangoByAgenciasDTO(Long idEmpresa, String agencias, Long estado);


	}
