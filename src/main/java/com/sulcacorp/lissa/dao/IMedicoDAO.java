package com.sulcacorp.lissa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sulcacorp.lissa.model.Medico;

@Repository
public interface IMedicoDAO extends JpaRepository<Medico, Long>{

}
