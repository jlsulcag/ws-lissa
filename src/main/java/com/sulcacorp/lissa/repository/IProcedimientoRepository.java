package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.Procedimiento;
import com.sulcacorp.lissa.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProcedimientoRepository extends JpaRepository<Procedimiento, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

    @Query("select a from Procedimiento a where a.estado =:estado order by a.denominacion asc")
    List<Procedimiento> findAllAct(@Param("estado") String estado) throws Exception;

}
