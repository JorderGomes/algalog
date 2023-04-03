package com.jorder.entregas.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;
    @Valid
    @NotNull
    private DestinatarioInput destinatario;
    @NotNull
    private BigDecimal taxa;

}
