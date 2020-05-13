package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.TipoDocumento;

@Repository
public interface ITipoDocumentoDAO extends JpaRepository<TipoDocumento, Long>{

}
