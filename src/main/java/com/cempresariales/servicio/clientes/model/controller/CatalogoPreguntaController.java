package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.clientes.model.service.CatalogoPreguntaServiceImp;
import com.cempresariales.servicio.commons.model.entity.CatalogoPregunta;

import com.cempresariales.servicio.commons.model.entity.Pregunta;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "catalogopregunta")
public class CatalogoPreguntaController {

	@Autowired
	private CatalogoPreguntaServiceImp repo;

	@GetMapping("/listar")
	public List<CatalogoPregunta> listar() {
		return repo.findAll();
	}

	@GetMapping("/findPreguntasByCategoria/{id}")
	public List<Pregunta> findPreguntasByCategoria(@PathVariable Long id) {
		return repo.findPreguntasByCategoria(id);
	}
	
	@GetMapping("/findByIdCategoria/{id}")
	public List<CatalogoPregunta> findByIdCategoria(@PathVariable Long id) {
		return repo.findByIdCategoria(id);
	}

	@GetMapping("/findByIdCatalogoPregunta/{id}")
	public CatalogoPregunta findByIdCatalogoPregunta(@PathVariable Long id) {
		return repo.findByIdCatalogoPregunta(id);
	}

	@GetMapping("/ver/{idCategoria}/{idPregunta}/{idPeso}")
	public CatalogoPregunta ver(@PathVariable Long idCategoria, @PathVariable Long idPregunta,
			@PathVariable Long idPeso) {
		return repo.findByPK(idCategoria, idPregunta, idPeso);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public CatalogoPregunta crear(@RequestBody CatalogoPregunta entidad) {
		return repo.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CatalogoPregunta editar(@RequestBody CatalogoPregunta entidad, @PathVariable Long id) {
		CatalogoPregunta entidadDb = repo.findByIdCatalogoPregunta(id);
		entidadDb = entidad;

		return repo.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		CatalogoPregunta entidadDb = repo.findByIdCatalogoPregunta(id);
		repo.delete(entidadDb.getCatalogoPreguntaPK());
	}
}
