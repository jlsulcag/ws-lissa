package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganizacionRepository extends JpaRepository<Organizacion, Long> {

    boolean existsByRazonSocial(String razonSocial);

    @Query("select a from Organizacion a where a.estado =:estado order by a.razonSocial asc")
    List<Organizacion> findAllAct(@Param("estado") String estado);
}
