package com.sulcacorp.lissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.UsuarioRol;

@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Long>{
	
	@Query("from UsuarioRol ur where ur.estado = '1'")
	List<UsuarioRol> findAllAct();
	
}
