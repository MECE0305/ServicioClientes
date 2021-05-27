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

import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.Respuesta;
import com.cempresariales.servicio.clientes.model.service.RespuestaServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "respuesta")
public class RespuestaController {

	@Autowired
	private RespuestaServiceImpl repo;

	@GetMapping("/listar")
	public List<Respuesta> listar() {
		return repo.findAll();
	}

	@GetMapping("/ver/{id}")
	public Respuesta ver(@PathVariable Long id) {
		return repo.findById(id);
	}

	@GetMapping("/findByCategoria/{idCategoria}")
	public List<Respuesta> findByCategoria(@PathVariable Long idCategoria) {
		return repo.findByCategoria(idCategoria);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Respuesta crear(@RequestBody Respuesta entidad) {
		return repo.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Respuesta editar(@RequestBody Respuesta entidad, @PathVariable Long id) {
		Respuesta entidadDb = repo.findById(id);
		entidadDb = entidad;

		return repo.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		repo.delete(id);
	}
	
	@PostMapping("/findRespuestaByChecklistEvaluacion")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Respuesta> findRespuestaByChecklistEvaluacion(@RequestBody List<ChecklistHasEvaluacion> lista) {
		return repo.findRespuestaByChecklistEvaluacion(lista);
	}
	
	@GetMapping("/findByIdEvaluacionIdChecklist/{idEvaluacion}/{idChecklist}")
	public List<Respuesta> findByIdEvaluacionIdChecklist(@PathVariable Long idEvaluacion, @PathVariable Long idChecklist) {
		return repo.findByIdEvaluacionIdChecklist(idEvaluacion, idChecklist);
	}

	@GetMapping("/findByIdEmpresa/{idEmpresa}")
	public List<Respuesta> findByIdEmpresa(@PathVariable Long idEmpresa) {
		return repo.findByIdEmpresa(idEmpresa);
	}

	@GetMapping("/findByIdEmpleado/{idEmpleado}")
	public List<Respuesta> findByIdEmpleado(@PathVariable Long idEmpleado) {
		return repo.findByIdEmpleado(idEmpleado);
	}

}
