package com.sulcacorp.lissa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sulcacorp.lissa.entity.EmpresaConvenio;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProcedimientoDTO {

    private Long idProcedimiento;

    @Valid
    private ServicioDTO servicio;

    private String denominacion;

    private String codigo;

    private LocalDateTime fechaReg;

    private String estado;
}
