package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.TipoIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoIngresoRepository extends JpaRepository<TipoIngreso, Long> {

    boolean existsByTipoIngreso(String tipoIngreso);

    @Query("select a from TipoIngreso a where a.estado =:estado order by a.tipoIngreso ASC ")
    List<TipoIngreso> findAllAct(@Param("estado") String estado);
}
