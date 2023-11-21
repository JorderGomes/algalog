package com.jorder.entregas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Produto {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 200)
    private String descricao;

    // @NotBlank
    private float preco;

    // @NotBlank
    private int qtdeEstoque;

    @ManyToOne
    private Vendedor vendedor;

}
