package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.EmpresaConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpresaConvenioRepository extends JpaRepository<EmpresaConvenio, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

    @Query("select a from EmpresaConvenio a where a.estado =:estado order by a.denominacion asc")
    List<EmpresaConvenio> findAllAct(@Param("estado") String estado) throws Exception;

}
