package com.sulcacorp.lissa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.entity.TipoPersona;

@Repository
public interface ITipoPersonaRepository extends JpaRepository<TipoPersona, Long>{
	
}
