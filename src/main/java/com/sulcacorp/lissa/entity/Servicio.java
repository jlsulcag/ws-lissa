package com.sulcacorp.lissa.entity;

import com.sulcacorp.lissa.util.Constante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SERVICIO")
public class Servicio {

    @Id
    @Column(name = "ID_SERVICIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(name = "DENOMINACION", unique = true, nullable = false, length = 150)
    private String denominacion;

    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @Column(name = "FECHA_REG")
    private LocalDateTime fechaReg;

    @Column(name = "estado")
    private String estado;

    /*
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private Set<Procedimiento> listProcedimientos = new HashSet<>();
     */

    @PrePersist
    void initCreatedAt() {
        fechaReg = LocalDateTime.now();
        setEstado(Constante.STATUS_REG_ENABLE);
    }
}
