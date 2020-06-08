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

import com.cempresariales.servicio.clientes.model.service.EmpresaServiceImp;
import com.cempresariales.servicio.commons.model.entity.Cliente;
import com.cempresariales.servicio.commons.model.entity.Empresa;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
@RequestMapping(value = "empresa")
public class EmpresaController {

	@Autowired
	private EmpresaServiceImp empresaServicio;

	@GetMapping("/listar")
	public List<Empresa> listarEmpresas() {
		return empresaServicio.findAll();
	}

	@GetMapping("/listarPorIdCli/{idCliente}")
	public List<Empresa> listarEmpresaCli(@PathVariable Long idCliente) {
		return empresaServicio.empresaAll(idCliente);
	}

	@GetMapping("/ver/{id}")
	public Empresa ver(@PathVariable Long id) {
		return empresaServicio.findById(id);
	}

	@PostMapping("/crear")
	public Empresa crear(@RequestBody Empresa empresa) {
		return empresaServicio.save(empresa);
	}

	@PostMapping("/findByClienteIdCliente")
	public List<Empresa> findByClienteIdCliente(@RequestBody Cliente cliente) {
		return empresaServicio.findByClienteIdCliente(cliente);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa editar(@RequestBody Empresa empresa, @PathVariable Long id) {
		return empresaServicio.save(empresa);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		empresaServicio.deleteById(id);
	}
}
