package com.cempresariales.servicio.clientes.model.controller;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.CategoriaTopDTO;
import com.cempresariales.servicio.clientes.model.dto.RolTopDTO;
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

import com.cempresariales.servicio.clientes.model.service.RolServiceImp;
import com.cempresariales.servicio.commons.model.entity.Rol;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "rol")
public class RolController {

	@Autowired
	private RolServiceImp rolService;

	@GetMapping("/listar")
	public List<Rol> listarRol() {
		return rolService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Rol verRol(@PathVariable Long id) {
		return rolService.findById(id);
	}

	@PostMapping("/crear")
	public Rol crear(@RequestBody Rol Rol) {
		return rolService.save(Rol);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol editar(@RequestBody Rol entidad, @PathVariable Long id) {
		Rol entidadDb = rolService.findById(id);
		entidadDb = entidad;
		entidadDb.setIdRol(entidad.getIdRol());

		return rolService.save(entidadDb);
	}

	@GetMapping("/findRolByArea/{idArea}")
	public List<Rol> findRolByArea(@PathVariable Long idArea) {
		return rolService.findRolByArea(idArea);
	}

	@PostMapping("/findRolByEmpleados")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Rol> findRolByEmpleados(@RequestBody Collection<Long> expresion) {
		return rolService.findRolByEmpleados(expresion);
	}

	@PostMapping("/rolesByAgencias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Rol> rolesByAgencias(@RequestBody Collection<Long> expresion) {
		return rolService.rolesByAgencias(expresion);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		rolService.delete(id);
	}

	@GetMapping("/findTop10ByEmpresa/{idEmpresa}/{orderBy}/{limit}")
	public List<RolTopDTO> findTop10ByEmpresa(@PathVariable Long idEmpresa, @PathVariable String orderBy, @PathVariable int limit) {
		return rolService.findTop10ByEmpresa(idEmpresa, orderBy, limit);
	}

	@GetMapping("/findByEmpresa/{idEmpresa}")
	public List<Rol> findByEmpresa(@PathVariable Long idEmpresa) {
		return rolService.findByEmpresa(idEmpresa);
	}

}
