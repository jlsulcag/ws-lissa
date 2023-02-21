package com.sulcacorp.lissa.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrganizacionDTO {

    private Long idOrganizacion;

    private String razonSocial;

    private String ruc;

    private String impuesto;

    private BigDecimal porcImpuesto;

    private String correo;

    private String direccion;

    private String telefono;

    private String estado;

}
