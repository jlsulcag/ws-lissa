package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

    @Query("select a from Servicio a where a.estado =:estado order by a.denominacion asc")
    List<Servicio> findAllAct(@Param("estado") String estado);

}
