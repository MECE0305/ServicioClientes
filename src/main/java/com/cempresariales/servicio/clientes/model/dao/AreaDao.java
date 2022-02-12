package com.cempresariales.servicio.clientes.model.dao;

import com.cempresariales.servicio.commons.model.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Area;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaDao extends JpaRepository<Area, Long>{

    @Query("select a from Area a INNER JOIN " +
            " Rol r ON r.areaIdArea.idArea = a.idArea INNER JOIN " +
            " RolHasEmpleado rhe ON r.idRol = rhe.rolHasEmpleadoPK.rolIdRol INNER JOIN " +
            " Empleado e ON rhe.rolHasEmpleadoPK.empleadoIdEmpleado = e.idEmpleado INNER JOIN " +
            " Agencia ag ON ag.idAgencia = e.agenciaIdAgencia.idAgencia " +
            " where ag.empresaIdEmpresa.idEmpresa = ?1 group by a.idArea")
    List<Area> findAreaByEmpresa(Long idEmpresa);

}
