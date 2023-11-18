package com.jorder.entregas.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valorUnitario;

    private int qtde;

    @ManyToOne
    @NonNull private Entrega entrega;

    @ManyToOne
    @NonNull private Produto produto;

    public float getValorTotal() {
		return this.qtde * this.valorUnitario;
	}

}
