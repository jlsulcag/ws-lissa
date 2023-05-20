package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.TarifarioEmpresaConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarifarioEmpresaConvenioRepository extends JpaRepository<TarifarioEmpresaConvenio, Long> {

    /*
    * Validar que el tarifario para un procedimiento y una empresa no debe tener doble registro
    * */
    @Query("select case when count(a) > 0 then true else false end from TarifarioEmpresaConvenio a where a.procedimiento.idProcedimiento =:idProcedimiento and a.empresaConvenio.idEmpresaConvenio =:idEmpresa ")
    boolean existsByProcedimientoAndEmpresa(@Param("idProcedimiento") Long idProcedimiento, @Param("idEmpresa") Long idEmpresa) throws Exception;

}
