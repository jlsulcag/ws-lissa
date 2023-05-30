package com.sulcacorp.lissa.dto.generic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BasicDTO {
    private String estado;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaReg;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaAct;
}
