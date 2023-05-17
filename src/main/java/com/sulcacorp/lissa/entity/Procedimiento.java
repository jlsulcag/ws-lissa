package com.sulcacorp.lissa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sulcacorp.lissa.util.Constante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROCEDIMIENTO")
public class Procedimiento {

    @Id
    @Column(name = "ID_PROCEDIMIENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedimiento;

    @JoinColumn(name = "ID_SERVICIO", nullable = false, foreignKey = @ForeignKey(name="FK_SERVICIO_PROCEDIMIENTO"))
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Servicio servicio;

    @Column(name = "DENOMINACION", unique = true, nullable = false, length = 150)
    private String denominacion;

    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @Column(name = "FECHA_REG")
    private LocalDateTime fechaReg;

    @Column(name = "estado")
    private String estado;

    @PrePersist
    void initCreatedAt() {
        fechaReg = LocalDateTime.now();
        setEstado(Constante.STATUS_REG_ENABLE);
    }
}
