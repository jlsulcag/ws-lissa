package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.util.Constante;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TARIFARIO_EMPRESA_CONVENIO")
public class TarifarioEmpresaConvenio {

    @Id
    @Column(name = "ID_TARIFARIO_EMPRESA_CONVENIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarifarioEmpresaConvenio;

    @JoinColumn(name = "ID_PROCEDIMIENTO", foreignKey = @ForeignKey(name="FK_PROCEDIMIENTO"))
    @ManyToOne
    private Procedimiento procedimiento;

    @JoinColumn(name = "ID_EMPRESA_CONVENIO", foreignKey = @ForeignKey(name="FK_EMPRESA_CONVENIO_TARIFARIO"))
    @ManyToOne
    private EmpresaConvenio empresaConvenio;

    @Column(name = "COSTO_PROCEDIMIENTO", nullable = false)
    private BigDecimal costoProcedimiento;

    @Column(name = "FECHA_REG", nullable = false)
    private LocalDateTime fechaReg;

    @Column(name = "estado", nullable = false)
    private String estado;

    @PrePersist
    void initCreatedAt() {
        fechaReg = LocalDateTime.now();
        setEstado(Constante.STATUS_REG_ENABLE);
    }
}
