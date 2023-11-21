package com.jorder.entregas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valorUnitario;

    private int qtde;

    @ManyToOne
    @JsonIgnore
    @NonNull private Entrega entrega;

    @ManyToOne
    @JsonIgnore
    @NonNull private Produto produto;

    public float getValorTotal() {
		return this.qtde * this.valorUnitario;
	}

}
