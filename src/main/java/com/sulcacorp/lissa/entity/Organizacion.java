package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper=false)/*Comparar solo los campos de esta clase y no la clase padre*/
@Data
@Entity
@Table(name = "ORGANIZACION")
public class Organizacion extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORGANIZACION")
    private Long id;

    @Column(name = "RAZON_SOCIAL", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "RUC", nullable = false, length = 20)
    private String ruc;

    @Column(name = "IMPUESTO", length = 50)
    private String impuesto;

    @Column(name = "PORC_IMPUESTO", nullable = false)
    private BigDecimal porcImpuesto;

    @Column(name = "CORREO", length = 100)
    private String correo;

    @Column(name = "DIRECCION", length = 200)
    private String direccion;

    @Column(name = "TELEFONO", length = 15)
    private String telefono;

}
