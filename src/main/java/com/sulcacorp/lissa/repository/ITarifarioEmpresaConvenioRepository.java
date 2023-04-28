package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.TarifarioEmpresaConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarifarioEmpresaConvenioRepository extends JpaRepository<TarifarioEmpresaConvenio, Long> {
}
