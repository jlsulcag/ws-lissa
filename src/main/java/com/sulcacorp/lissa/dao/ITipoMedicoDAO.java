package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.TipoMedico;

@Repository
public interface ITipoMedicoDAO extends JpaRepository<TipoMedico, Long>{
	
}
