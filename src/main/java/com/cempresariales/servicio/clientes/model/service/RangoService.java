package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

public interface RangoService {

	public List<RangoDesempenio> findAll();

	public RangoDesempenio findById(Long id);

	public RangoDesempenio save(RangoDesempenio entidad);

	public void deleteById(Long id);
}
