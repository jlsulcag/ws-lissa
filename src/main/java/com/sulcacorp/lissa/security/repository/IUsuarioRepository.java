package com.sulcacorp.lissa.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("from Usuario u where u.estado = '1'")
	List<Usuario> findAllAct();
	
	
	@Query("from Usuario u where u.nombreUsuario =:userName and u.estado = '1' ")
	Optional<Usuario> findByNombre(@Param("userName") String userName);
	
	boolean existsByNombreUsuario(String nombreUsuario);

}
