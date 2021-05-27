package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.clientes.model.service.EmpleadoHasRolServiceImp;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleadoPK;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "empleadorol")
public class EmpleadoHasRolController {

	@Autowired
	private EmpleadoHasRolServiceImp repo;

	@GetMapping("/listar")
	public List<RolHasEmpleado> listarAgencias() {
		return repo.findAll();
	}

	@GetMapping("/ver/{id}")
	public RolHasEmpleado verAgencia(@PathVariable RolHasEmpleadoPK id) {
		return repo.findById(id);
	}

	@GetMapping("/findByEmpleado/{id}")
	public List<RolHasEmpleado> findByEmpleado(@PathVariable Long id) {
		return repo.findByEmpleado(id);
	}

	@GetMapping("/findByRol/{id}")
	public List<Empleado> findByRol(@PathVariable Long id) {
		return repo.findByRol(id);
	}

	@PostMapping("/crear")
	public RolHasEmpleado crear(@RequestBody RolHasEmpleado entidad) {
		return repo.save(entidad);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable RolHasEmpleadoPK id) {
		repo.delete(id);
	}
}
