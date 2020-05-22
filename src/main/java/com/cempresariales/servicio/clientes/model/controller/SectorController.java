package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.clientes.model.service.SectorServiceImp;
import com.cempresariales.servicio.commons.model.entity.Sector;

@RestController
@RequestMapping(value = "sector")
public class SectorController {

	@Autowired
	private SectorServiceImp service;

	@GetMapping("/listar")
	public List<Sector> listarSector() {
		return service.findAll();
	}

	@GetMapping("/ver/{id}")
	public Sector verItem(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping(path = "/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Sector crear(@RequestBody Sector entidad) {
		return service.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Sector editar(@RequestBody Sector entidad, @PathVariable Long id) {
		Sector entidadDb = service.findById(id);

		entidad.setIdSector(entidadDb.getIdSector());

		return service.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		service.deleteById(id);
	}
}
