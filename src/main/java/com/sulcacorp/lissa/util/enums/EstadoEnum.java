package com.sulcacorp.lissa.util.enums;

import lombok.Data;

public enum EstadoEnum {
    ANULADO("0"),
    ACTIVO("1");

    private String  estado;

    EstadoEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
