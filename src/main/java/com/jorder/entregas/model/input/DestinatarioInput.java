package com.jorder.entregas.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DestinatarioInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;

}
