package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.EmpresaConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaConvenioRepository extends JpaRepository<EmpresaConvenio, Long> {
}
