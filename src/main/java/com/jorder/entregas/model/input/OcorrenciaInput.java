package com.jorder.entregas.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OcorrenciaInput {

    @NotBlank
    private String descricao;

}
