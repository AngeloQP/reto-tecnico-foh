package com.reto.backend.service;

import com.reto.backend.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    void registrarCliente(List<? extends Cliente> clientes);

    List<Cliente> listarClientes();
}
