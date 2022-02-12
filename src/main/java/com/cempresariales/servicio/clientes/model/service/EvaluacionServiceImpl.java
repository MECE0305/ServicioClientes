package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import com.cempresariales.servicio.clientes.model.dao.EvaluacionDao;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionDao evaluacionDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evaluacion> findByParams(BuscadorDTO buscador) {

        try {

            System.out.println("DATOS DE OBJETO BUSCAR: " + buscador.getEstado());

            StringBuilder queryString = new StringBuilder("select eva from Evaluacion eva where eva.idEvaluacion > 0 ");

            if (buscador.getTipoFecha() != null) {
                switch (buscador.getTipoFecha()) {
                    case "1":
                        queryString.append(" and eva.creaEvaluacion between ?1 and ?2 ");
                        break;
                    case "2":
                        queryString.append(" and eva.fechaEvaluacion between ?3 and ?4 ");
                        break;

                    default:
                        break;
                }
            }

            if (buscador.getTipoFecha() != null) {
                switch (buscador.getTipoFecha()) {
                    case "1":
                        queryString.append(" and eva.creaEvaluacion between ?1 and ?2 ");
                        break;
                    case "2":
                        queryString.append(" and eva.fechaEvaluacion between ?3 and ?4 ");
                        break;

                    default:
                        break;
                }
            }

            if (buscador.getEstado() != null) {
                if (buscador.getIdCategoria() != 0)
                    queryString.append(" and eva.estadoEvaluacionIdEstado.idEstado ?5 ");
            }

            if (buscador.getTipoPersona() != null) {
                switch (buscador.getTipoPersona()) {
                    case "1":
                        queryString.append(" and eva.idUsuarioCrea = ?6 ");
                        break;
                    case "2":
                        queryString.append(" and eva.idUsuarioPlanifica = ?7 ");
                        break;
                    case "3":
                        queryString.append(" and eva.idUsuarioEjecuta = ?8 ");
                        break;

                    default:
                        break;
                }
            }

            if (buscador.getIdCategoria() != null) {
                queryString.append(" and eva.idEvaluacion in "
                        + "(Select cle from ChecklistHasEvaluacion cle where cle.checklist.idChecklist in "
                        + "(select ccp from ChecklistHasCatalogoPregunta ccp where ccp.CatalogoPregunta.Categoria.idCategoria = ?9 ))");
            }

            if (buscador.getActivo() != null) {
                if (buscador.getActivo().equals("1") || buscador.getActivo().equals("0"))
                    queryString.append(" and eva.activoEvaluacion = ?10 ))");
            }

            queryString.append("ORDER BY eva.creaEvaluacion desc");

            Query query = entityManager.createQuery(queryString.toString());

            if (buscador.getTipoFecha() != null) {
                if (buscador.getTipoFecha().equals("1")) {
                    query.setParameter(1, buscador.getFechaInicio());
                    query.setParameter(2, buscador.getFechaFin());
                }
                if (buscador.getTipoFecha().equals("2")) {
                    query.setParameter(3, buscador.getFechaInicio());
                    query.setParameter(4, buscador.getFechaFin());
                }
            }

            if (buscador.getEstado() != null) {
                if (buscador.getIdCategoria() != 0)
                    query.setParameter(5, buscador.getEstado());
            }

            if (buscador.getTipoPersona() != null) {
                switch (buscador.getTipoPersona()) {
                    case "1":
                        query.setParameter(6, buscador.getIdPersona());
                        break;
                    case "2":
                        query.setParameter(7, buscador.getIdPersona());
                        break;
                    case "3":
                        query.setParameter(8, buscador.getIdPersona());
                        break;

                    default:
                        break;
                }
            }

            if (buscador.getIdCategoria() != null) {
                if (!buscador.getIdCategoria().equals(0))
                    query.setParameter(9, buscador.getIdCategoria());
            }

            if (buscador.getActivo() != null) {
                if (buscador.getActivo().equals("1"))
                    query.setParameter(10, true);

                if (buscador.getActivo().equals("0"))
                    query.setParameter(10, false);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Evaluacion> findEvaByAgencias(Collection<Long> expresion) {
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

            StringBuilder queryString = new StringBuilder("select eva from Evaluacion eva where eva.idEmpleado in "
                    + " (select e.idEmpleado from Empleado e where e.agenciaIdAgencia.idAgencia in " + "(" + cadena
                    + ")" + ")");

            queryString.append(" ORDER BY eva.creaEvaluacion desc");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public List<MedicionDTO> findEvaByAgenciasDTO(Long idEmpresa, String agencias,Long estado) {

        try {

            StringBuilder queryString = new StringBuilder("select new com.cempresariales.servicio.clientes.model.dto.MedicionDTO(ep.idEmpresa, ag.idAgencia, emp.idEmpleado,e.idEvaluacion,ee.idEstado, ep.nombreEmpresa, ag.nombreAgencia, emp.nombreEmpleado, rl.nombreRol,rd.nombreRango, rd.colorRango, e.observacionEvaluacion, round(e.puntajeEvaluacion,2), e.horaInicioEvaluacion, e.horaFinEvaluacion,e.atencionEvaluacion, e.contactoEvaluacion, e.esperaEvaluacion, ep.imagenEmpresa, emp.fotoEmpleado, ag.emailAgencia, ag.telefonoAgencia, ag.activoAgencia, ag.direccionAgencia ) " +
                    " from RangoDesempenio rd" +
                    " join Empresa ep on ep.idEmpresa = rd.empresa.idEmpresa" +
                    " join Agencia ag on ag.empresaIdEmpresa.idEmpresa = ep.idEmpresa" +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join RolHasEmpleado re on re.rolHasEmpleadoPK.empleadoIdEmpleado = emp.idEmpleado" +
                    " join Rol rl on rl.idRol = re.rolHasEmpleadoPK.rolIdRol" +
                    " join Evaluacion e on e.idEmpleado = emp.idEmpleado" +
                    " join EstadoEvaluacion ee on ee.idEstado = e.estadoEvaluacionIdEstado.idEstado" +
                    " where e.puntajeEvaluacion between rd.minimoRango and rd.maximoRango" +
                    " and ag.idAgencia in " + "(" + agencias + ") ");


            if(idEmpresa == 0)
                queryString.append(" and ep.idEmpresa > 0");
            else queryString.append(" and ep.idEmpresa = "+idEmpresa);
            if(estado != 0)
                queryString.append(" and ee.idEstado = "+estado);



            queryString.append(" group by ep.idEmpresa, ag.idAgencia, emp.idEmpleado,e.idEvaluacion,ee.idEstado, ep.nombreEmpresa, ag.nombreAgencia, emp.nombreEmpleado, rl.nombreRol,rd.nombreRango, rd.colorRango, e.observacionEvaluacion, e.puntajeEvaluacion, e.horaInicioEvaluacion, e.horaFinEvaluacion,e.atencionEvaluacion, e.contactoEvaluacion, e.esperaEvaluacion, ep.imagenEmpresa, emp.fotoEmpleado, ag.emailAgencia, ag.telefonoAgencia, ag.activoAgencia, ag.direccionAgencia" +
                    " order by emp.nombreEmpleado ");

            System.out.println("queryString: " + queryString.toString());

            TypedQuery<MedicionDTO> consulta =
                    entityManager.createQuery(queryString.toString(), MedicionDTO.class);
            List<MedicionDTO> results = consulta.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evaluacion> findAll() {
        return (List<Evaluacion>) evaluacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evaluacion findById(Long id) {
        return evaluacionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionDao.save(evaluacion);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        evaluacionDao.deleteById(id);
    }

    @Override
    public List<Evaluacion> findBySegmentacion(Long idRegion, Long idZona, Long idProvincia, Long idCiudad,
                                               Long idZonaEstructural) {
        try {

            String signoCiudad = ">";
            String signoProvincia = ">";
            String signoZonaE = ">";
            String signoRegion = ">";
            String signoZona = ">";
            if (idCiudad != 0) {
                signoCiudad = "=";
            }
            if (idProvincia != 0) {
                signoProvincia = "=";

            }
            if (idZonaEstructural != 0) {
                signoZonaE = "=";

            }
            if (idRegion != 0) {
                signoRegion = "=";

            }
            if (idZona != 0) {
                signoZona = "=";

            }

            StringBuilder queryString = new StringBuilder("select eva from Evaluacion eva where eva.idEmpleado in "
                    + "( " + "select idEmpleado from Empleado e where e.agencia.zonaEstructuralHasCiudad.ciudad.idCiudad "
                    + signoCiudad + " ?1 " + "and e.agencia.zonaEstructuralHasCiudad.ciudad.provinciaIdProvincia.idProvincia "
                    + signoProvincia + " ?2 "
                    + "and e.agencia.zonaEstructuralHasCiudad.ciudad.idCiudad in (select ze.ciudad.idCiudad from ZonaEstructuralHasCiudad ze where ze.zonaEstructural.idZonaEstructural "
                    + signoZonaE + " ?3 ) "
                    + "and e.agencia.zonaEstructuralHasCiudad.ciudad.provinciaIdProvincia.idProvincia in "
                    + "(select zp.provincia.idProvincia from ZonaHasProvincia zp where zp.zona.idZona in "
                    + "(select zr.zona.idZona from RegionHasZona zr where zr.region.idRegion " + signoRegion
                    + " ?4 and zr.zona.idZona " + signoZona + " ?5 ) " + ") " + ")");

            queryString.append("ORDER BY eva.creaEvaluacion desc");

            Query query = entityManager.createQuery(queryString.toString());

            if (idCiudad != 0) {
                query.setParameter(1, idCiudad);
            } else {
                query.setParameter(1, new Long(0));
            }
            if (idProvincia != 0) {
                query.setParameter(2, idProvincia);
            } else {
                query.setParameter(2, new Long(0));
            }
            if (idZonaEstructural != 0) {
                query.setParameter(3, idZonaEstructural);
            } else {
                query.setParameter(3, new Long(0));
            }
            if (idRegion != 0) {
                query.setParameter(4, idRegion);
            } else {
                query.setParameter(4, new Long(0));
            }
            if (idZona != 0) {
                query.setParameter(5, idZona);
            } else {
                query.setParameter(5, new Long(0));
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Evaluacion> findByFiltroTabClienteAndRol(Long idCliente, Long idEmpresa, Long idSector, Long idAgencia,
                                                         Long idEmpleado, Long idRol, Long idArea) {
        try {

            String signoCliente = ">";
            String signoEmpresa = ">";
            String signoSector = ">";
            String signoAgencia = ">";
            String signoEmpleado = ">";
            String signoRol = ">";
            String signoArea = ">";

            if (idCliente != 0) {
                signoCliente = "=";
            }
            if (idEmpresa != 0) {
                signoEmpresa = "=";

            }

            if (idSector != 0) {
                signoSector = "=";

            }
            if (idAgencia != 0) {
                signoAgencia = "=";

            }
            if (idEmpleado != 0) {
                signoEmpleado = "=";

            }
            if (idRol != 0) {
                signoRol = "=";
            }

            if (idArea != 0) {
                signoArea = "=";
            }

            StringBuilder queryString = new StringBuilder("select eva from Evaluacion eva where eva.idEmpleado in "
                    + "(select idEmpleado from Empleado e where e.idEmpleado in "
                    + "(select re.empleado.idEmpleado from RolHasEmpleado re where re.empleado.idEmpleado "
                    + signoEmpleado + " ?5 and re.rol.idRol " + signoRol + " ?6"
                    + "and re.rol.idRol in (select r.idRol from Rol r where r.areaIdArea.idArea " + signoArea + " ?7)"
                    + ")" + "and e.agenciaIdAgencia.idAgencia  " + signoAgencia + " ?1 "
                    + "and e.agenciaIdAgencia.empresaIdEmpresa.idEmpresa " + signoEmpresa + " ?2 "
                    + "and e.agenciaIdAgencia.empresaIdEmpresa.clienteIdCliente.idCliente " + signoCliente + " ?3 "
                    + "and e.agenciaIdAgencia.empresaIdEmpresa.sectorIdSector.idSector " + signoSector + " ?4 )");

            queryString.append("ORDER BY eva.creaEvaluacion desc");

            Query query = entityManager.createQuery(queryString.toString());

            if (idEmpleado != 0) {
                query.setParameter(5, idEmpleado);
            } else {
                query.setParameter(5, new Long(0));
            }
            if (idAgencia != 0) {
                query.setParameter(1, idAgencia);
            } else {
                query.setParameter(1, new Long(0));
            }
            if (idEmpresa != 0) {
                query.setParameter(2, idEmpresa);
            } else {
                query.setParameter(2, new Long(0));
            }
            if (idCliente != 0) {
                query.setParameter(3, idCliente);
            } else {
                query.setParameter(3, new Long(0));
            }
            if (idSector != 0) {
                query.setParameter(4, idSector);
            } else {
                query.setParameter(4, new Long(0));
            }
            if (idRol != 0) {
                query.setParameter(6, idRol);
            } else {
                query.setParameter(6, new Long(0));
            }
            if (idArea != 0) {
                query.setParameter(7, idArea);
            } else {
                query.setParameter(7, new Long(0));
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Evaluacion> findByEvaluacionCategoria(Long idEvaluacion, Long idCategoria) {
        return evaluacionDao.findByEvaluacionCategoria(idEvaluacion, idCategoria);
    }

    @Override
    public String reporteBloquesbyAgencias(Collection<Long> IdAgencias) {

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


            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.EncabezadoReporteBloquesDTO(cl.idChecklist, ev.idEvaluacion, ze.nombreZonaEstructural, c.nombreCiudad, ag.nombreAgencia, emp.nombreEmpleado, r.nombreRol, a.nombreArea) " +
                    " from Evaluacion ev " +
                    " join RolHasEmpleado em on em.rolHasEmpleadoPK.empleadoIdEmpleado = ev.idEmpleado" +
                    " join Rol r on r.idRol = em.rolHasEmpleadoPK.rolIdRol" +
                    " join Area a on a.idArea = r.areaIdArea.idArea" +
                    " join Empleado emp on ev.idEmpleado = emp.idEmpleado" +
                    " join Agencia ag on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join ZonaEstructuralHasCiudad zec on zec.idZonaCiudad = ag.zonaCiudad.idZonaCiudad " +
                    " join Ciudad c on ag.zonaCiudad.zonaEstructuralIdCiudad = c.idCiudad" +
                    " join ZonaEstructural ze on ze.idZonaEstructural = zec.zonaEstructuralIdZonaEstructural" +
                    " join Checklist cl on r.idRol = cl.rolIdRol.idRol " +
                    " where ag.idAgencia in " + "(" + cadena + ")";
            TypedQuery<EncabezadoReporteBloquesDTO> consulta =
                    entityManager.createQuery(queryStr, EncabezadoReporteBloquesDTO.class);
            List<EncabezadoReporteBloquesDTO> results = consulta.getResultList();

System.out.println("DATA 1: "+results.size());

            List<ReporteBloquesDTO> list = new ArrayList<>();

            for (EncabezadoReporteBloquesDTO encabezado : results) {
                ReporteBloquesDTO reporte = new ReporteBloquesDTO();
                List<DetalleReporteBloquesDTO> listaDetalle =  new ArrayList<>();
                listaDetalle = detalleBloquesbyAgencias(encabezado.getIdChecklist(), encabezado.getIdEvaluacion());
                reporte.setEncabezado(encabezado);
                reporte.setDetalle(listaDetalle);

                list.add(reporte);
            }



            Gson gson = new Gson();
            String jsonInString = gson.toJson(list);



            return jsonInString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public List<DetalleReporteBloquesDTO> detalleBloquesbyAgencias(Long idChecklist, Long idEvaluacion) {

        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO(cat.nombreCategoria ,cp.categoria.idCategoria, avg(r.valorCalculadoRespuesta), count(r.idRespuesta), sum(case when r.cumpleRespuesta = true then 1 else 0 end) )" +
                    " from Respuesta r " +
                    " join CatalogoPregunta cp on r.idCatalogoPregunta = cp.idCatalogoPregunta" +
                    " join Categoria cat on cat.idCategoria = cp.categoria.idCategoria" +
                    " where r.checklistHasEvaluacion.checklist.idChecklist = ?1 and r.checklistHasEvaluacion.evaluacion.idEvaluacion = ?2" +
                    " group by cp.categoria.idCategoria";
            TypedQuery<DetalleReporteBloquesDTO> consulta =
                    entityManager.createQuery(queryStr, DetalleReporteBloquesDTO.class);
            consulta.setParameter(1, idChecklist);
            consulta.setParameter(2, idEvaluacion);
            List<DetalleReporteBloquesDTO> results = consulta.getResultList();


            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<DetalleReporteBloquesDTO>();
        }
    }


}
