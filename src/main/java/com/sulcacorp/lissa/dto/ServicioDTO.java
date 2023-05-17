package com.sulcacorp.lissa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
/*ignorar un valor de campo durante la serialización si el valor de ese campo es nulo*/
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ServicioDTO {

    private Long idServicio;

    private String denominacion;

    private String codigo;

    private LocalDateTime fechaReg;

    private String estado;
}
