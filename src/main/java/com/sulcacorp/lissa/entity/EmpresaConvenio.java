package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.entity.generic.GenericEntity;
import com.sulcacorp.lissa.util.Constante;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
* Esta entidad permite gestionar las empresas aseguradoras, convenios y tarifarios
* */
@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPRESA_CONVENIO")
public class EmpresaConvenio extends GenericEntity {

    @Id
    @Column(name = "ID_EMPRESA_CONVENIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresaConvenio;

    @Column(name = "DENOMINACION", unique = true, nullable = false, length = 150)
    private String denominacion;

    @Column(name = "CONFIG_CARTA")
    private String configCarta;
}
