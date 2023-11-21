package com.jorder.entregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jorder.entregas.model.ItemEntrega;

public interface ItemEntregaRepository extends JpaRepository<ItemEntrega, Long>  {
    List<ItemEntrega> findByEntregaId(Long entregaId);

    @Query(value = "select sum(valor_unitario * qtde) from item_entrega where entrega_id = :entrega_id", nativeQuery = true)
    public float sumPrecoItens(Long entrega_id);

}
