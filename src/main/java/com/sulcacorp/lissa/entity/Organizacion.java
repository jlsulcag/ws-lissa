package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericModel;
import com.sulcacorp.lissa.util.Constante;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper=false)/*Comparar solo los campos de esta clase y no la clase padre*/
@Data
@Entity
@Table(name = "ORGANIZACION")
public class Organizacion extends GenericModel {

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

    /*
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    */

    /*
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "FECHA_ACT")
    private LocalDateTime fechaAct;
    */

    @PrePersist
    void initCreatedAt() {
        setFechaReg(LocalDateTime.now());
        setEstado(Constante.STATUS_REG_ENABLE);
    }

    @PreUpdate
    void initUpdatedAt() {
        setFechaAct(LocalDateTime.now());
    }

}
