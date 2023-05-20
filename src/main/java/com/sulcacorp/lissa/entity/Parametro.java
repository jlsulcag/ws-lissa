package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Table(name = "PARAMETRO")
//@Entity
public class Parametro  extends GenericEntity {

    private Long idParametro;

    private Long idTabla;

    private Long idSubTabla;

    private String nombreTabla;

    private String denominacion;

    private String descripcion;

}
