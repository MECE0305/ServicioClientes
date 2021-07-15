package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO;
import com.cempresariales.servicio.clientes.model.dto.EncabezadoReporteBloquesDTO;
import com.cempresariales.servicio.clientes.model.dto.ReporteBloquesDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.RolDao;
import com.cempresariales.servicio.commons.model.entity.Rol;

@Service
public class RolServiceImp implements IRolService {

    @Autowired
    private RolDao rolDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Rol> findAll() {
        return rolDao.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    public void delete(Long id) {
        rolDao.deleteById(id);
    }

    @Override
    public List<Rol> findRolByArea(Long idArea) {
        return rolDao.findRolByArea(idArea);
    }

    @Override
    public List<Rol> findRolByEmpleados(Collection<Long> expresion) {
        try {

            Iterator<Long> iterator = expresion.iterator();
            String cadena = "";
            int x = 0;
            while (iterator.hasNext()) {

                cadena += iterator.next() + ",";
                if (x == expresion.size() - 1) {
                    cadena = cadena.substring(0, cadena.length() - 1);
                }

                x++;
            }

            StringBuilder queryString = new StringBuilder(
                    "select re.rol from RolHasEmpleado re where re.empleado.empleadoPK.idEmpleado  in " + "(" + cadena + ") group by re.rol.nombreRol");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Rol> rolesByAgencias(Collection<Long> IdAgencias) {

        try {
            Iterator<Long> iterator = IdAgencias.iterator();
            String cadena = "";
            int x = 0;
            while (iterator.hasNext()) {

                cadena += iterator.next() + ",";
                if (x == IdAgencias.size() - 1) {
                    cadena = cadena.substring(0, cadena.length() - 1);
                }

                x++;
            }

            System.out.println("CADENA" + cadena);


            String queryStr = "SELECT r from Rol r where r.idRol in " +
                    "(SELECT re.rolHasEmpleadoPK.rolIdRol FROM RolHasEmpleado re " +
                    "WHERE re.rolHasEmpleadoPK.empleadoIdEmpleado in( " +
                    " select emp.idEmpleado from Empleado emp " +
                    " where emp.agenciaIdAgencia.idAgencia in (" + cadena + ")))";
            Query query = entityManager.createQuery(queryStr.toString());


            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
