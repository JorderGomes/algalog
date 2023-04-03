package com.jorder.entregas.service;

import com.jorder.entregas.model.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> getClientes();

    Cliente getClienteById(Long id);

    Cliente createCliente(Cliente cliente);
    Cliente editCliente(Long id, Cliente cliente);
    void deleteCliente(Long id);

}
