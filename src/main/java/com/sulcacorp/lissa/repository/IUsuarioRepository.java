package com.sulcacorp.lissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("from Usuario u where u.estado = '1'")
	List<Usuario> findAllAct();

}
