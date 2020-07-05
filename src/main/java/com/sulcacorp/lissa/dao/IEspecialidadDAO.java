package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Especialidad;

@Repository
public interface IEspecialidadDAO  extends JpaRepository<Especialidad, Long>{

}
