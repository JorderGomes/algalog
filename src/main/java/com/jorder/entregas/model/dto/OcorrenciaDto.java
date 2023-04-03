package com.jorder.entregas.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class OcorrenciaDto {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
