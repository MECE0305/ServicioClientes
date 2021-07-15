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

import com.cempresariales.servicio.clientes.model.service.AgenciaServiceImp;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "agencia")
public class AgenciaController {

	@Autowired
	private AgenciaServiceImp agenciaService;

	@GetMapping("/listar")
	public List<Agencia> listarAgencias() {
		return agenciaService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Agencia verAgencia(@PathVariable Long id) {
		return agenciaService.findById(id);
	}

	@PostMapping("/crear")
	public Agencia crear(@RequestBody Agencia agencia) {
		return agenciaService.save(agencia);
	}

	@PostMapping("/findByEmpresaIdEmpresa")
	public List<Agencia> findByEmpresaIdEmpresa(@RequestBody Empresa empresa) {
		return agenciaService.findByEmpresaIdEmpresa(empresa);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Agencia editar(@RequestBody Agencia entidad, @PathVariable Long id) {
		Agencia agenciaDb = agenciaService.findById(id);
		agenciaDb = entidad;

		return agenciaService.save(agenciaDb);
	}

	@DeleteMapping("/eliminarAgencia/{id}")
	public void eliminar(@PathVariable Long id) {
		agenciaService.delete(id);
	}
	
	
	
}
