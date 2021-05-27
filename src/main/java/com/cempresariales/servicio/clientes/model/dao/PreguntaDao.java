package com.cempresariales.servicio.clientes.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Pregunta;

public interface PreguntaDao extends JpaRepository<Pregunta, Long> {

}
