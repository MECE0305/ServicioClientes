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

import com.cempresariales.servicio.commons.model.entity.Zona;
import com.cempresariales.servicio.clientes.model.service.ZonaServiceImp;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "zona")
public class ZonaController {

	@Autowired
	private ZonaServiceImp zonaServicio;

	@GetMapping("/listar")
	public List<Zona> listarZonas() {
		return zonaServicio.findAll();
	}

	@GetMapping("/ver/{id}")
	public Zona ver(@PathVariable Long id) {
		return zonaServicio.findById(id);
	}

	@PostMapping("/crear")
	public Zona crear(@RequestBody Zona zona) {
		return zonaServicio.save(zona);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Zona editar(@RequestBody Zona entidad, @PathVariable Long id) {
		Zona entidadDb = zonaServicio.findById(id);

		entidadDb.setNombreZona(entidad.getNombreZona());
		entidadDb.setActivoZona(entidad.getActivoZona());
		entidadDb.setZonaHasProvinciaList(entidad.getZonaHasProvinciaList());

		return zonaServicio.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		zonaServicio.delete(id);
	}
}
