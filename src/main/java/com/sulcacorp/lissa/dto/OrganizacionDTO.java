package com.sulcacorp.lissa.dto;

import com.sulcacorp.lissa.dto.generic.BasicDTO;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrganizacionDTO extends BasicDTO {

    private Long idOrganizacion;

    private String razonSocial;

    private String ruc;

    private String impuesto;

    private BigDecimal porcImpuesto;

    private String correo;

    private String direccion;

    private String telefono;

}
