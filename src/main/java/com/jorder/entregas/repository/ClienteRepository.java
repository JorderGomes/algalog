package com.jorder.entregas.repository;

import com.jorder.entregas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    
    List<Cliente> findByNomeContaining(String nome);
    
    Optional<Cliente> findByEmail(String email);
    
}
