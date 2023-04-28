package com.sulcacorp.lissa.repository;

import com.sulcacorp.lissa.entity.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcedimientoRepository extends JpaRepository<Procedimiento, Long> {
}
