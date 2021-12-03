package com.sulcacorp.lissa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sulcacorp.lissa.model.EstadoCivil;

@Repository
public interface IEstadoCivilDAO extends JpaRepository<EstadoCivil, Long>{

	@Query("from EstadoCivil a where a.estado = 1")
	List<EstadoCivil> findAllAct();
		
	@Modifying
	@Transactional
	@Query("update EstadoCivil a set estado = 0 where a.idEstadoCivil =:id")
	void deleteLogic(@Param("id") Long id);
	
}
