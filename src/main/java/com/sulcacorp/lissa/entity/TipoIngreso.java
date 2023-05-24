package com.sulcacorp.lissa.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sulcacorp.lissa.entity.generic.GenericEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TIPO_INGRESO")
@JsonPropertyOrder({"idTipoIngreso", "tipoIngreso", "fechaReg", "fechaAct", "estado"})
public class TipoIngreso extends GenericEntity {

    @Id
    @Column(name = "ID_TIPO_INGRESO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoIngreso;

    @Column(name = "TIPO_INGRESO", nullable = false, length=30)
    private String tipoIngreso;

}
