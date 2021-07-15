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

import com.cempresariales.servicio.clientes.model.service.EmpleadoServiceImp;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping(value = "empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceImp empleadoService;

    @GetMapping("/listar")
    public List<Empleado> listarEmpleado() {
        return empleadoService.findAll();
    }

    @GetMapping("/listarAll")
    public List<Empleado> buscarAllEmpleado() {
        return empleadoService.findAll();
    }

    @GetMapping("/ver/{id}")
    public Empleado verItem(@PathVariable Long id) {
        return empleadoService.findById(id);
    }

    @PostMapping("/crear")
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoService.save(empleado);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado editar(@RequestBody Empleado entidad, @PathVariable Long id) {
        Empleado entidadDb = empleadoService.findById(id);
        entidadDb = entidad;

        return empleadoService.save(entidadDb);
    }

    @PostMapping("/findByAgenciaIdAgencia")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Empleado> findByAgenciaIdAgencia(@RequestBody Agencia agencia) {
        return empleadoService.findByAgenciaIdAgencia(agencia);
    }

    @PostMapping("/findEmpleadoByAgencias")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Empleado> findEmpleadoByAgencias(@RequestBody Collection<Long> expresion) {
        return empleadoService.findEmpleadoByAgencias(expresion);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.delete(id);
    }
}
