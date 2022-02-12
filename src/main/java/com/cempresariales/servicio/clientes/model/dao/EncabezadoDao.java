package com.cempresariales.servicio.clientes.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Encabezado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EncabezadoDao extends JpaRepository<Encabezado, Long>{

    @Query("SELECT enc FROM Encabezado enc INNER JOIN " +
            " EvaluacionHasEncabezado ec ON enc.idEncabezado = ec.encabezado.idEncabezado INNER JOIN " +
            " Evaluacion ev ON ec.evaluacion.idEvaluacion = ev.idEvaluacion " +
            " WHERE ev.idEmpleado in (select emp.idEmpleado from Empleado emp INNER JOIN " +
            " Agencia ag on ag.idAgencia = emp.agenciaIdAgencia.idAgencia INNER JOIN " +
            " Empresa e on e.idEmpresa = ag.empresaIdEmpresa.idEmpresa where e.idEmpresa = :id)" +
            " group by enc.idEncabezado ")
    public List<Encabezado> listaEncabezadoPorEmpresa(Long id);

}
