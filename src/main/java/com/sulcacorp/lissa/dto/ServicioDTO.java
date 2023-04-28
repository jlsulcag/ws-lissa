package com.sulcacorp.lissa.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServicioDTO {

    private Long idServicio;

    private String denominacion;

    private String codigo;

    private LocalDateTime fechaReg;

    private String estado;
}
