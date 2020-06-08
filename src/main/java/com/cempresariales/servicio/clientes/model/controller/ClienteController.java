package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import javax.ws.rs.core.Response;

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

import com.cempresariales.servicio.clientes.model.service.ClienteServiceImp;
import com.cempresariales.servicio.commons.model.entity.Cliente;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteServiceImp promedioServicio;

	@GetMapping("/listar")
	public List<Cliente> listarCliente() {
		return promedioServicio.findAll();
	}

	@GetMapping("/ver/{id}")
	public Cliente verItem(@PathVariable Long id) {
		return promedioServicio.findById(id);
	}

	@PostMapping(path = "/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) {
		return promedioServicio.save(cliente);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente editar(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteDb = promedioServicio.findById(id);
		clienteDb = cliente;
		clienteDb.setIdCliente(cliente.getIdCliente());

		return promedioServicio.save(clienteDb);
	}

	@DeleteMapping("/eliminar/{id}")
	public Response eliminar(@PathVariable Long id) {
		try {

			promedioServicio.deleteById(id);
			return Response.ok(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			return Response.noContent().build();
		}
	}
}
