package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericModel;
import com.sulcacorp.lissa.util.Constante;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* Esta entidad permite gestionar los beneficios de las empresas aseguradoras y convenios (Copago Fijo, Descuento por planilla, etc)
* */
@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPRESA_CONVENIO_ITEM")
public class EmpresaConvenioItem extends GenericModel {

    @Id
    @Column(name = "ID_EMPRESA_CONVENIO_ITEM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresaConvenioItem;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CONVENIO", nullable = false, foreignKey = @ForeignKey(name="FK_EMPRESA_CONVENIO"))
    private EmpresaConvenio empresaConvenio;

    @Column(name = "DENOMINACION", nullable = false, length = 150)
    private String denominacion;

    @Column(name = "PORCENTAJE_CUBIERTO_DEFAULT", nullable = false)
    private int porcentajeCubiertoDefault;

}
