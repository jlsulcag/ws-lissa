package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.EmpresaConvenioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaConvenioItemRepository extends JpaRepository<EmpresaConvenioItem, Long> {

}
