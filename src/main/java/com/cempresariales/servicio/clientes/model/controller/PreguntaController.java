package com.cempresariales.servicio.clientes.model.controller;

import java.util.Collection;
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

import com.cempresariales.servicio.clientes.model.service.PreguntaServiceImp;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "pregunta")
public class PreguntaController {

	@Autowired
	private PreguntaServiceImp repo;

	@GetMapping("/listar")
	public List<Pregunta> listar() {
		return repo.findAll();
	}

	@GetMapping("/ver/{id}")
	public Pregunta ver(@PathVariable Long id) {
		return repo.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Pregunta crear(@RequestBody Pregunta entidad) {
		return repo.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Pregunta editar(@RequestBody Pregunta entidad, @PathVariable Long id) {
		Pregunta entidadDb = repo.findById(id);
		entidadDb = entidad;
		entidadDb.setIdPregunta(entidad.getIdPregunta());

		return repo.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		repo.delete(id);
	}
	
	@PostMapping("/findPreguntasByCategorias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Pregunta> findPreguntasByCategorias(@RequestBody Collection<Long> expresion) {
		return repo.findPreguntasByCategorias(expresion);
	}
	
}
