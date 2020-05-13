package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.EstadoCivil;

@Repository
public interface IEstadoCivilDAO extends JpaRepository<EstadoCivil, Long>{
	
}
