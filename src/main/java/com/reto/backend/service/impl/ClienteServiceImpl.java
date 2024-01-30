package com.reto.backend.service.impl;

import com.reto.backend.entities.Cliente;
import com.reto.backend.repository.ClienteRepository;
import com.reto.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void registrarCliente(List<? extends Cliente> clientes) {
        clienteRepository.saveAll(clientes);
    }

    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}
