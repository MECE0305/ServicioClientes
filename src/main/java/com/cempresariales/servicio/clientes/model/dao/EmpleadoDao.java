package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.EmpleadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import org.springframework.data.jpa.repository.Query;

public interface EmpleadoDao extends JpaRepository<Empleado, Long> {

    @Query("select new com.cempresariales.servicio.clientes.model.dto.EmpleadoDTO" +
            " (emp.idEmpleado, emp.activoEmpleado, emp.apellidoEmpleado, emp.ciEmpleado, emp.creaEmpleado, " +
            " emp.fotoEmpleado, emp.generoEmpleado, emp.mailEmpleado, emp.nombreEmpleado, emp.telefonoEmpleado, " +
            " emp.agenciaIdAgencia.idAgencia, emp.agenciaIdAgencia.nombreAgencia, rhe.rol.nombreRol )" +
            " from Empleado emp join RolHasEmpleado rhe on rhe.rolHasEmpleadoPK.empleadoIdEmpleado = emp.idEmpleado" +
            " where emp.activoEmpleado = true order by emp.apellidoEmpleado asc")
    List<EmpleadoDTO> listarEmpleadosDTO();
}
