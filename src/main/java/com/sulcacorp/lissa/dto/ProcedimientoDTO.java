package com.sulcacorp.lissa.dto;

import com.sulcacorp.lissa.entity.Servicio;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcedimientoDTO {

    private Long idProcedimiento;

    private Servicio servicio;

    private String denominacion;

    private String codigo;

    private LocalDateTime fechaReg;

    private String estado;
}
