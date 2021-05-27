package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.clientes.model.service.ChecklistCatalogoPreguntaServiceImp;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPregunta;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasCatalogoPreguntaPK;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "checklist-catalogo-pregunta")
public class ChecklistCatalogoPreguntaController {

	@Autowired
	private ChecklistCatalogoPreguntaServiceImp repo;

	@GetMapping("/listar")
	public List<ChecklistHasCatalogoPregunta> listar() {
		return repo.findAll();
	}

	@GetMapping("/findByChecklistID/{id}")
	public List<ChecklistHasCatalogoPregunta> findByChecklistID(@PathVariable Long id) {
		return repo.findByChecklistID(id);
	}

	@GetMapping("/findByCategoria/{id}")
	public List<ChecklistHasCatalogoPregunta> findByCategoria(@PathVariable Long id) {
		return repo.findByCategoria(id);
	}

	@GetMapping("/findByCategoriaChecklist/{idCategoria}/{idChecklist}")
	public List<ChecklistHasCatalogoPregunta> findByCategoriaChecklist(@PathVariable Long idCategoria,
			@PathVariable Long idChecklist) {
		return repo.findByCategoriaChecklist(idCategoria, idChecklist);
	}

	@GetMapping("/ver/{id}")
	public ChecklistHasCatalogoPregunta ver(@PathVariable ChecklistHasCatalogoPreguntaPK id) {
		return repo.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ChecklistHasCatalogoPregunta crear(@RequestBody ChecklistHasCatalogoPregunta entidad) {
		return repo.save(entidad);
	}

	@DeleteMapping("/eliminar/{idChecklist}/{idCategoria}/{idPregunta}/{idPeso}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long idChecklist, @PathVariable Long idCategoria, @PathVariable Long idPregunta,
			@PathVariable Long idPeso) {
		ChecklistHasCatalogoPreguntaPK pk = new ChecklistHasCatalogoPreguntaPK(idChecklist, idCategoria, idPregunta,
				idPeso);
		repo.delete(pk);
	}
}
