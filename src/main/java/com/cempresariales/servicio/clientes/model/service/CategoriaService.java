package com.cempresariales.servicio.clientes.model.service;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Categoria;

public interface CategoriaService {

	public List<Categoria> findAll();
	public Categoria findById(Long id);
	public Categoria save(Categoria entidad);
	public void delete(Long id);
	
	List<Categoria> findCategoriasByChecklist(Collection<Long> expresion);
	
}
