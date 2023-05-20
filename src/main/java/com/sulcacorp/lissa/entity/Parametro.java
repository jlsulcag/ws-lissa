package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
//@Table(name = "PARAMETRO")
//@Entity
public class Parametro  extends GenericModel {

    private Long idParametro;

    private Long idTabla;

    private Long idSubTabla;

    private String nombreTabla;

    private String denominacion;

    private String descripcion;

}
