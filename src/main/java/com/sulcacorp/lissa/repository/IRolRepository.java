package com.sulcacorp.lissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{

	@Query("from Rol r where r.estado = '1'")
	List<Rol> findAllAct();
	
}
