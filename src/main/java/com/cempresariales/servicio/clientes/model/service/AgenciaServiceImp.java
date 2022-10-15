package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.AgenciaDao;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Service
public class AgenciaServiceImp implements IAgenciaService {

    @Autowired
    private AgenciaDao agenciaDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Agencia> findAll() {
        return agenciaDao.findAll();
    }

    @Override
    public Agencia findById(Long id) {
        return agenciaDao.findById(id).orElse(null);
    }

    @Override
    public Agencia save(Agencia agencia) {
        return agenciaDao.save(agencia);
    }

    @Override
    public void delete(Long id) {
        agenciaDao.deleteById(id);
    }

    @Override
    public List<Agencia> findByEmpresaIdEmpresa(Empresa empresa) {
        return agenciaDao.findByEmpresaIdEmpresa(empresa);
    }

    @Override
    public List<AgenciasTopDTO> findTop10ByEmpresa(Long idEmpresa, String orderBy, int limit) {
        try {

            String order = "3";
            if (orderBy == null || orderBy.equals(""))
                order = "ag.idAgencia";
            if (orderBy.equals("total"))
                order = "3";
            if (orderBy.equals("seccion"))
                order = "4";
            if (orderBy.equals("pregunta"))
                order = "5";

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO(ag.idAgencia, ag.nombreAgencia, sum(eva.promedioTotal), sum(eva.promedioSeccion), sum(eva.promedioPregunta) )" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado" +
                    " where ag.empresaIdEmpresa.idEmpresa = ?1 group by ag.nombreAgencia order by " + order + " desc";
            TypedQuery<AgenciasTopDTO> consulta =
                    entityManager.createQuery(queryStr, AgenciasTopDTO.class).setMaxResults(limit);
            consulta.setParameter(1, idEmpresa);
            List<AgenciasTopDTO> results = consulta.getResultList();


            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<AgenciasTopDTO>();
        }
    }

    @Override
    public List<AgenciasDTO> findPromedioAgenciasPorEmpresa(List<Long> IdAgencias, Long idEmpresa) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia, ROUND(avg(eva.puntajeEvaluacion),2), ag.ceoAgencia)" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado" +
                    " where ag.idAgencia in :cadena and ag.empresaIdEmpresa.idEmpresa = ?1" +
                    " group by ag.nombreAgencia ";
            System.out.println("Sentencia : " + queryStr);
            //   Query query = entityManager.createQuery(queryStr.toString());

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class).setMaxResults(10);
            query.setParameter("cadena", IdAgencias);
            query.setParameter(1, idEmpresa);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }


    @Override
    public List<AgenciasDTO> findAgenciasPorRol(Long idEmpresa, Long idRol, List<Long> IdAgencias) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia,  avg(ev.puntajeEvaluacion), r.nombreRol)" +
                    " from Empleado emp INNER JOIN" +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia INNER JOIN" +
                    " RolHasEmpleado re ON emp.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado INNER JOIN" +
                    " Rol r ON re.rolHasEmpleadoPK.rolIdRol = r.idRol INNER JOIN" +
                    " Evaluacion ev ON emp.idEmpleado = ev.idEmpleado" +
                    " WHERE  ag.empresaIdEmpresa.idEmpresa = ?1 and r.idRol = ?2 and ag.idAgencia in :cadena " +
                    " GROUP BY ag.nombreAgencia " +
                    " order by r.nombreRol, ag.nombreAgencia ";

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class).setMaxResults(10);
            query.setParameter(1, idEmpresa);
            query.setParameter(2, idRol);
            query.setParameter("cadena", IdAgencias);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<AgenciasDTO> findAgenciasPorRolZonaEncabezado(Long idEmpresa, Long idRol, List<Long> IdAgencias, Long idEncabezado, Long idzonaEs) {
        try {


            StringBuilder queryStr = new StringBuilder("select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia, ROUND(avg(ev.puntajeEvaluacion),2) as promedio, r.nombreRol)" +
                    " from Agencia ag INNER JOIN " +
                    " Empleado e ON ag.idAgencia = e.agenciaIdAgencia.idAgencia INNER JOIN " +
                    " RolHasEmpleado re ON e.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado INNER JOIN " +
                    " Rol r ON re.rolHasEmpleadoPK.rolIdRol = r.idRol INNER JOIN" +
                    " Evaluacion ev ON e.idEmpleado = ev.idEmpleado INNER JOIN " +
                    " EvaluacionHasEncabezado ehe ON ehe.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion = ev.idEvaluacion " +
                    " WHERE  ag.idAgencia in :cadena");

            if (idEmpresa != 0) {
                queryStr.append(" and ag.empresaIdEmpresa.idEmpresa = ?1");
            }
            if (idzonaEs != 0) {
                queryStr.append(" and ag.idZonaEstructural = ?2");
            }
            if (idEncabezado != 0) {
                queryStr.append(" and ehe.evaluacionHasEncabezadoPK.encabezadoIdEncabezado = ?3");
            }
            if (idRol != 0) {
                queryStr.append(" and r.idRol = ?4");
            }

            queryStr.append(" group by ag.idAgencia order by promedio");

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr.toString(), AgenciasDTO.class).setMaxResults(10);

            query.setParameter("cadena", IdAgencias);

            if (idEmpresa != 0) {
                query.setParameter(1, idEmpresa);
            }
            if (idzonaEs != 0) {
                query.setParameter(2, idzonaEs);
            }
            if (idEncabezado != 0) {
                query.setParameter(3, idEncabezado);
            }
            if (idRol != 0) {
                query.setParameter(4, idRol);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public AgenciasDTO findPromedioEmpresaRol(Long idEmpresa) {

        String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(emp.idEmpresa, emp.nombreEmpresa, ROUND(avg(ev.puntajeEvaluacion),2) , r.nombreRol)" +
                " from Agencia ag INNER JOIN " +
                " Empleado e ON ag.idAgencia = e.agenciaIdAgencia.idAgencia INNER JOIN " +
                " Empresa emp ON ag.empresaIdEmpresa.idEmpresa = emp.idEmpresa INNER JOIN " +
                " RolHasEmpleado re ON e.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado INNER JOIN " +
                " Rol r ON re.rolHasEmpleadoPK.rolIdRol = r.idRol INNER JOIN" +
                " Evaluacion ev ON e.idEmpleado = ev.idEmpleado" +
                " WHERE  emp.idEmpresa = ?1";


        TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class);

        query.setParameter(1, idEmpresa);

        return query.getSingleResult();

    }

    @Override
    public List<Agencia> findByZonaEstructural(Long idZonaEst) {
        return agenciaDao.findByZonaEstructural(idZonaEst);
    }


    @Override
    public List<AgenciasDTO> findPromedioPorAgencia(Long idAgencia) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia, avg(eva.puntajeEvaluacion), ag.ceoAgencia)" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado" +
                    " where ag.idAgencia = ?1";

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class).setMaxResults(10);

            query.setParameter(1, idAgencia);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }

    public AgenciasDTO findPromedioPorEmpresa(Long idEmpresa) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(e.idEmpresa, e.nombreEmpresa, ROUND(avg(ev.puntajeEvaluacion),2), ag.ceoAgencia)" +
                    " FROM  Agencia ag INNER JOIN " +
                    " Empleado emp ON ag.idAgencia = emp.agenciaIdAgencia.idAgencia INNER JOIN " +
                    " Evaluacion ev ON ev.idEmpleado = emp.idEmpleado INNER JOIN " +
                    " Empresa e ON e.idEmpresa = ag.empresaIdEmpresa.idEmpresa " +
                    " where e.idEmpresa = ?1";


            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class);

            query.setParameter(1, idEmpresa);

            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AgenciasDTO();
        }


    }

    @Override
    public List<AgenciasDTO> findPromedioPorEmpresaZonaEnca(List<Long> IdAgencias, Long idEmpresa, Long idEncabezado, Long idZonaEs) {
        try {

            StringBuilder queryStr = new StringBuilder("select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia, ROUND(avg(eva.puntajeEvaluacion),2) as promedio, ag.ceoAgencia)" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado " +
                    " join EvaluacionHasEncabezado ehe on ehe.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion = eva.idEvaluacion " +
                    " where ag.idAgencia in :cadena ");

            if (idEmpresa != 0) {
                queryStr.append(" and ag.empresaIdEmpresa.idEmpresa = ?1");
            }
            if (idEncabezado != 0) {
                queryStr.append(" and ehe.evaluacionHasEncabezadoPK.encabezadoIdEncabezado = ?2");
            }
            if (idZonaEs != 0) {
                queryStr.append(" and ag.idZonaEstructural = ?3");
            }

            queryStr.append(" group by ag.idAgencia order by promedio desc");

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr.toString(), AgenciasDTO.class).setMaxResults(10);
            query.setParameter("cadena", IdAgencias);

            if (idEmpresa != 0) {
                query.setParameter(1, idEmpresa);
            }
            if (idEncabezado != 0) {
                query.setParameter(2, idEncabezado);
            }
            if (idZonaEs != 0) {
                query.setParameter(3, idZonaEs);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
