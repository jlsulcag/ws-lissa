package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Consulta;

@Repository
public interface IConsultaDAO extends JpaRepository<Consulta, Long>{

}
