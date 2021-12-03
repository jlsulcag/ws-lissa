package com.sulcacorp.lissa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.TipoMedico;

@Repository
public interface ITipoMedicoDAO extends JpaRepository<TipoMedico, Long>{

	@Query("from TipoMedico a where a.estado = 1")
	List<TipoMedico> findAllAct();
	
}
