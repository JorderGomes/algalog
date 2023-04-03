package com.jorder.entregas.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteIdInput {
    @NotNull
    private Long id;
}
