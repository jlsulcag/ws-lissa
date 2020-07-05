package com.sulcacorp.lissa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Persona;

@Repository
public interface IPersonaDAO extends JpaRepository<Persona, Long>{
	
	@Query("From Persona a where a.numeroDocumentoIdentidad =:docNumber order by a.apellidoPaterno asc, a.apellidoMaterno asc")			
	Persona buscarXDni(@Param("docNumber") String docNumber);

	@Query("From Persona a where concat(a.nombres, ' ', a.apellidoPaterno, ' ', a.apellidoPaterno) like %:fullName% order by a.apellidoPaterno asc, a.apellidoMaterno asc")
	List<Persona> listarFullName(@Param("fullName") String fullName);

	@Query("From Persona a where a.tipoDocumento.idTipoDocumento =:typeDoc and a.numeroDocumentoIdentidad =:docNumber order by a.apellidoPaterno asc, a.apellidoMaterno asc")
	Persona buscarXDoc(@Param("typeDoc") Long typeDOc, @Param("docNumber") String docNumber);
	
}
